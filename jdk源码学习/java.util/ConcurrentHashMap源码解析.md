ConcurrentHashMap源码解析
====

ConcurrentHashMap 在 jdk 1.7 、1.8 版的实现上略有不同，下面我们逐一解析。

## 基于 1.7

我们先来看下结构图，如下：

![img](https://i.loli.net/2019/05/08/5cd1d2c5ce95c.jpg)

如上图所示，关键是 Segment 、HashEntry 组成。

> HashEntry 和 HashMap 的 1.7 实现类似，是数组加链表实现。



我们先来看一下核心成员变量。

```java
 		/**
     * The segments, each of which is a specialized hash table.
     * Segment 数组，存放数据时需要先定位到具体的 Segment 中。
     */
    final Segment<K,V>[] segments;

    transient Set<K> keySet;
    transient Set<Map.Entry<K,V>> entrySet;
```

其中 Segment 数组中的一个元素对应一个 HashEntry 数组，HashEntry 和 HashMap 的实现类似。

Segment 是 ConcurrentHashMap 的内部类，主要实现如下：

```java
static final class Segment<K,V> extends ReentrantLock implements Serializable {
       
        private static final long serialVersionUID = 2249069246763182397L;

        /**
         * The maximum number of times to tryLock in a prescan before
         * possibly blocking on acquire in preparation for a locked
         * segment operation. On multiprocessors, using a bounded
         * number of retries maintains cache acquired while locating
         * nodes.
         */
        static final int MAX_SCAN_RETRIES =
            Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;

        /**
         * The per-segment table. Elements are accessed via
         * entryAt/setEntryAt providing volatile semantics.
         * 和 HashMap 中的 HashEntry 类似，是存放数据的桶
         */
        transient volatile HashEntry<K,V>[] table;

        /**
         * The number of elements. Accessed only either within locks
         * or among other volatile reads that maintain visibility.
         */
        transient int threadUnSafeCount;

        /**
         * The total number of mutative operations in this segment.
         * Even though this may overflows 32 bits, it provides
         * sufficient accuracy for stability checks in CHM isEmpty()
         * and size() methods.  Accessed only either within locks or
         * among other volatile reads that maintain visibility.
         */
        transient int modCount;

        /**
         * The table is rehashed when its size exceeds this threshold.
         * (The value of this field is always <tt>(int)(capacity *
         * loadFactor)</tt>.)
         */
        transient int threshold;

        /**
         * The load factor for the hash table.  Even though this value
         * is same for all segments, it is replicated to avoid needing
         * links to outer object.
         * @serial
         */
        final float loadFactor;
}
```

再来看看 HashEntry 的实现：

```java
static final class HashEntry<K,V> {
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;

        HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
}
```

可见，和 HashMap 中的 Entry 非常类似，唯一的区别：核心数据 value  和链表都由 **volatile 修饰**，保证了获取时线程间的可见性。

从原理上来说：ConcurrentHashMap 采用了**「分段锁」**技术，其中 Segment 继承于 ReentrantLock。不会像 HashTable 那样无论是 put 还是 get 都需要同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel 个(即 Segment 数组数量)线程并发。每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment，从而提高了并发的性能。

> cm：可以理解为把锁进行了细化，原来 HashTable 的一把锁变成了 ConcurrentHashMap 的多把锁，从而增加了并发能力。

### put 方法

```java
public V put(K key, V value) {
        Segment<K,V> s;
        if (value == null)
            throw new NullPointerException();
        int hash = hash(key);
        int j = (hash >>> segmentShift) & segmentMask;
        if ((s = (Segment<K,V>)UNSAFE.getObject          // nonvolatile; recheck
             (segments, (j << SSHIFT) + SBASE)) == null) //  in ensureSegment
            s = ensureSegment(j);
        return s.put(key, hash, value, false);
}
```

可见，先**通过 key 定位到 Segment**，然后在对应的 Segment 中进行具体的 put 操作。

我们继续追踪 Segment 的 put 方法：

```java
final V put(K key, int hash, V value, boolean onlyIfAbsent) {
  					// 1.将当前 Segment 中的 table 通过 key 的 hashcode 定位到 HashEntry
            HashEntry<K,V> node = tryLock() ? null :
                scanAndLockForPut(key, hash, value);
            V oldValue;
            try {
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;
                HashEntry<K,V> first = entryAt(tab, index);
                for (HashEntry<K,V> e = first;;) {
                  	// 2.遍历 HashEntry，若不为空，判断 key 是否相等，相等则覆盖旧值。
                    if (e != null) {
                        K k;
                        if ((k = e.key) == key ||
                            (e.hash == hash && key.equals(k))) {
                            oldValue = e.value;
                            if (!onlyIfAbsent) {
                                e.value = value;
                                ++modCount;
                            }
                            break;
                        }
                        e = e.next;
                    }
                  	// 3.若 HashEntry 为空，则需要新建一个 HashEntry 并加入到 Segment 中，同时先判断是否需要扩容
                    else {
                        if (node != null)
                            node.setNext(first);
                        else
                            node = new HashEntry<K,V>(hash, key, value, first);
                        int c = threadUnSafeCount + 1;
                        if (c > threshold && tab.length < MAXIMUM_CAPACITY)
                            rehash(node);
                        else
                            setEntryAt(tab, index, node);
                        ++modCount;
                        threadUnSafeCount = c;
                        oldValue = null;
                        break;
                    }
                }
            } finally {
              	// 4.最后释放在 1 中所获取当前 Segment 的锁
                unlock();
            }
            return oldValue;
}
```

虽然 HashEntry 中的 value 使用了 volatile 修饰，但不能保证原子性（只保证可见性），所以上面的 put 操作仍然需要加锁，即对应 

代码  `HashEntry<K,V> node = tryLock() ? null : scanAndLockForPut(key, hash, value);`。

首先，先尝试获取锁，如果获取失败，说明已经有其他线程竞争，此时利用 scanAndLockForPut() **自旋获取锁**，如下：

```java
private HashEntry<K,V> scanAndLockForPut(K key, int hash, V value) {
            HashEntry<K,V> first = entryForHash(this, hash);
            HashEntry<K,V> e = first;
            HashEntry<K,V> node = null;
            int retries = -1; // negative while locating node
            // 尝试自旋获取锁
            while (!tryLock()) {
                HashEntry<K,V> f; // to recheck first below
                if (retries < 0) {
                    if (e == null) {
                        if (node == null) // speculatively create node
                            node = new HashEntry<K,V>(hash, key, value, null);
                        retries = 0;
                    }
                    else if (key.equals(e.key))
                        retries = 0;
                    else
                        e = e.next;
                }
                // 如果重试次数达到了 MAX_SCAN_RETRIES，则改为阻塞锁获取，保证能获取成功。
                else if (++retries > MAX_SCAN_RETRIES) {
                    lock();
                    break;
                }
                else if ((retries & 1) == 0 &&
                         (f = entryForHash(this, hash)) != first) {
                    e = first = f; // re-traverse if entry changed
                    retries = -1;
                }
            }
            return node;
        }
```

### get 方法

```java
public V get(Object key) {
        Segment<K,V> s; // manually integrate access methods to reduce overhead
        HashEntry<K,V>[] tab;
        int h = hash(key);
        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;
        if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
                (tab = s.table) != null) {
            for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                    (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                 e != null; e = e.next) {
                K k;
                if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                    return e.value;
            }
        }
        return null;
}
```

可见，只需要将 key 通过 hash 后定位到具体的 Segment，再通过一次 hash 定位到具体元素上。

因为 HashEntry 中的 value 属性由 volatile 修饰，保证了可见性，所以每次获取的都是最新值。

**get 方法无需加锁**，因此效率很高。

## 基于 1.8

既然 1.7 解决了并发问题，并且可以支持 N 个 Segment 次的并发，为什么 1.8 版本 还要重新实现？

其实这个问题和 HashMap 1.7 中的问题类似，就是**查询大链表的效率太低了**（特别是链表比较长时）。

我们先来看下底层的结构图，如下：

![Java8 ConcurrentHashMap 存储结构（图片来自 javadoop）](https://snailclimb.gitee.io/javaguide/docs/java/collection/images/java8_concurrenthashmap.png)



> 看到上面的图，有没有觉得和 1.8 版本的 HashMap 很类似呢？

1.8 中直接摒弃了 1.7 中 Segment 分段锁的设计，而是采用了 **CAS + synchronized** 保证线程安全。

另一个改变，是将 1.7 中存放数据的 **HashEntrty 改为了 Node**，当然，两者的作用是相同的。（猜测可能是为了方便区分）

代码如下：

```java
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        volatile V val;
        volatile Node<K,V> next;

        Node(int hash, K key, V val, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public final K getKey()       { return key; }
        public final V getValue()     { return val; }
        public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
        public final String toString(){ return key + "=" + val; }
        public final V setValue(V value) {
            throw new UnsupportedOperationException();
        }
}
```

> 类似的，val 和 next 都使用了 volatile 修饰，保证了可见性。

### put 方法

按照惯例，我们先来看看 put 方法：

```java
public V put(K key, V value) {
        return putVal(key, value, false);
}
```

```java
final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
  			// 根据 key 计算 hashcode
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
          	// 判断是否需要进行初始化
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
          	// f 是根据当前 key 定位出的 Node,如果为空，则表示当前位置可以写入数据，利用 CAS 尝试写入，失败则自旋保证成功
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
          	// 如果当前位置的 hashcode == MOVED（即 -1），则需要进行扩容
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
              	// 如果以上都不满足，则利用 synchronized 锁写入数据
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                              	// key 相等则覆盖旧值
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                              	// 追加到链表尾部
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                      	// 红黑树，按照红黑树操作
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                  	// 如果大于阈值，则转换为红黑树
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
```



### get 方法

```java
public V get(Object key) {
        Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
        int h = spread(key.hashCode());
  			// 根据 key 计算出来的 hashcode 寻址
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (e = tabAt(tab, (n - 1) & h)) != null) {
          	// 如果在桶上则直接返回
            if ((eh = e.hash) == h) {
                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                    return e.val;
            }
          	// 若是红黑树，按照红黑树方式查找
            else if (eh < 0)
                return (p = e.find(h, key)) != null ? p.val : null;
          	// 若是链表则按照链表方式遍历
            while ((e = e.next) != null) {
                if (e.hash == h &&
                    ((ek = e.key) == key || (ek != null && key.equals(ek))))
                    return e.val;
            }
        }
        return null;
}
```

对比 1.7 版本可知，1.8 在数据结构上做了大的改动。采用**「红黑树」**之后可以保证查询效率（`O(logn)`），甚至取消了 ReentrantLock 改为了 synchronized，从侧面也可以看出在新版 JDK 中对 synchronized 优化是很到位的（以前都说 synchronized 的性能不如 ReentrantLock）。



这块也是面试的重点内容，通常的套路是：

1. 谈谈你理解的 HashMap，讲讲其中的 get put 过程。
2. 1.8 做了什么优化？
3. 是线程安全的嘛？
4. 不安全会导致哪些问题？
5. 如何解决？有没有线程安全的并发容器？
6. ConcurrentHashMap 是如何实现的？ 1.7、1.8 实现有何不同？为什么这么做？













## 简介


## 继承体系

## 动手实践

## 源码解析

## 属性


## 构造方法


## 普通方法


## 面试常见问题

























































### reference

- https://crossoverjie.top/2018/07/23/java-senior/ConcurrentHashMap/