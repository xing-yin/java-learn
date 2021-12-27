HashMap 源码学习
====

HashMap设计与实现是个非常高频的面试题，所以我会在这进行相对详细的源码解读，主要围绕: 

* HashMap内部实现基本点分析
* 容量(capcity)和负载系数(load factor)
* 树化 

首先，我们来一起看看HashMap内部的结构，它可以看作是数组(Node[] table)和链表结合组成的复合结构，数组被分为一个个桶(bucket)，通过哈希值决定了键值对在这个 数组的寻址;哈希值相同的键值对，则以链表形式存储，你可以参考下面的示意图。这里需要注意的是，**如果链表大小超过阈值(TREEIFY_THRESHOLD, 8)**，图中的链表就会被改造为树形结构。

<div align="center"> <img src="pics/9-2.png" width="500" style="zoom:100%"/> </div><br>

从非拷贝构造函数的实现来看，这个表格(数组)似乎并没有在最初就初始化好，仅仅设置了一些初始值而已。

``` java
public HashMap(int initialCapacity, float loadFactor){  
    // ... 
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}
```

所以，我们深刻怀疑，HashMap也许是按照lazy-load原则，在首次使用时被初始化(拷贝构造函数除外，我这里仅介绍最通用的场景)。既然如此，我们去看看put方法实现，似 乎只有一个putVal的调用:

	    public V put(K key, V value) {
			return putVal(hash(key), key, value, false, true);
		}

看来主要的秘密似乎藏在putVal里面，到底有什么秘密呢?为了节省空间，我这里只截取了putVal比较关键的几部分。

``` java
final V putVal(int hash, K key, V value, boolean onlyIfAbent,
               boolean evit) {
    Node<K,V>[] tab; Node<K,V> p; int , i;
    if ((tab = table) == null || (n = tab.length) = 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == ull)
        tab[i] = newNode(hash, key, value, nll);
    else {
        // ...
        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for first 
           treeifyBin(tab, hash);
        //  ... 
     }
}
```

从putVal方法最初的几行，我们就可以发现几个有意思的地方: 

* 如果表格是null，resize方法会负责初始化它，这从tab = resize()可以看出。 

* resize方法兼顾两个职责，创建初始存储表格，或者在容量不满足需求的时候，进行扩容(resize)。 

* 在放置新的键值对的过程中，如果发生下面条件，就会发生扩容。

		if (++size > threshold) 
		resize();

* 具体键值对在哈希表中的位置(数组index)取决于下面的位运算:
     
    	i=(n-1)&hash
     
仔细观察哈希值的源头，我们会发现，它并不是key本身的hashCode，而是来自于HashMap内部的另外一个hash方法。

注意，为什么这里需要将高位数据移位到低位进行异或运算呢?

这是**因为有些数据计算出的哈希值差异主要在高位，而HashMap里的哈希寻址是忽略容量以上的高位的，那么这种处理就可以有效避免类似情况下的哈希碰撞。**

``` java
static final int hash(Object kye) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>>16);
}
```

* 链表结构(这里叫bin)，会在达到一定门限值时，发生树化，我稍后会分析为什么HashMap需要对bin进行处理。 

可以看到，putVal方法本身逻辑非常集中，从初始化、扩容到树化，全部都和它有关，推荐你阅读源码的时候，可以参考上面的主要逻辑。 

我进一步分析一下身兼多职的resize方法，很多朋友都反馈经常被面试官追问它的源码设计。

``` java
final Node<K,V>[] resize() {
    // ...
    else if ((newCap = oldCap << 1) < MAXIMUM_CAPACIY &&
                oldCap >= DEFAULT_INITIAL_CAPAITY)
        newThr = oldThr << 1; // double there
       // ... 
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {  
        // zero initial threshold signifies using defaultsfults
        newCap = DEFAULT_INITIAL_CAPAITY;
        newThr = (int)(DEFAULT_LOAD_ATOR* DEFAULT_INITIAL_CAPACITY；
    }
    if (newThr ==0) {
        float ft = (float)newCap * loadFator;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?(int)ft : Integer.MAX_VALUE);
    }
    threshold = neThr;
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newap];
    table = n；
    // 移动到新的数组结构 e 数组结构 
   }

```

依据resize源码，不考虑极端情况(容量理论最大极限由 MAXIMUM_CAPACITY 指定，数值为 1<<30，也就是2的30次方)，我们可以归纳为:

* **门限值等于(负载因子)x(容量)**，如果构建HashMap的时候没有指定它们，那么就是依据相应的默认常量值。

* 门限通常是以**倍数进行调**整 (newThr = oldThr << 1)，我前面提到，根据putVal中的逻辑，当元素个数超过门限大小时，则调整Map大小。 

* 扩容后，需要将老的数组中的**元素重新放置到新的数组**，这是扩容的一个主要开销来源。

### 3.容量、负载因子和树化 

- 为什么我们需要在乎容量和负载因子呢?

这是因为容量和负载系数决定了可用的桶的数量，**空桶太多会浪费空间，如果使用的太满则会严重影响操作的性能**。极端情况下，假设只有一个桶，那么它就退化成了链表，完全不能提供所谓常数时间存的性能。

> @yx
>
> 你想啊，为了节省空间，你将负载系数设置的很小，下一次操作稍微新增几个元素就得频繁扩容(数组复制很消耗性能)；
> 同样，你将负载系数设置的很大,显著增加冲突，性能一样降低。

- 既然容量和负载因子这么重要，我们在实践中应该如何选择呢?

如果能够知道HashMap要存取的键值对数量，可以考虑预先设置合适的容量大小。具体数值我们可以根据扩容发生的条件来做简单预估，根据前面的代码分析，我们知道它需要符合计算条件:
  
	负载因子 * 容量 > 元素数量
	
所以，**预先设置的容量需要满足，大于“预估元素数量/负载因子”，同时它是2的幂数，** 结论已经非常清晰了。

- 对于负载因子，我建议:

	* 如果没有特别需求，不要轻易进行更改，因为JDK自身的默认负载因子是非常符合通用场景的需求的。 
	* 如果确实需要调整，建议不要设置超过0.75的数值，因为会显著增加冲突，降低HashMap的性能。 
	* 如果使用太小的负载因子，按照上面的公式，预设容量值也进行调整，否则可能会导致更加频繁的扩容，增加无谓的开销，本身访问性能也会受影响。

我们前面提到了树化改造，对应逻辑主要在putVal和treeifyBin中。

``` java
final void treeifyBin(Node<K,V>[] tab, int hash) {
    int n, index; Node<K,V> e;
    if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
        resize();
    else if ((e = tab[index = (n - 1) & hash]) != null) {
        // 树化改造逻辑
    }
}
```

上面是精简过的treeifyBin示意，综合这两个方法，树化改造的逻辑就非常清晰了，可以理解为，当bin的数量大于TREEIFY_THRESHOLD时: 

- 如果容量小于MIN_TREEIFY_CAPACITY，只会进行简单的扩容。

- 如果容量大于MIN_TREEIFY_CAPACITY ，则会进行树化改造。

#### 那么，为什么HashMap要树化呢? 

**本质上这是个安全问题。** 因为在元素放置过程中，如果一个对象哈希冲突，都被放置到同一个桶里，则会形成一个链表，我们知道链表查询是线性的，会严重影响存取的性能。

而在现实世界，构造哈希冲突的数据并不是非常复杂的事情，恶意代码就可以利用这些数据大量与服务器端交互，导致服务器端CPU大量占用，这就构成了哈希碰撞拒绝服务攻击， 国内一线互联网公司就发生过类似攻击事件。

本节从Map相关的几种实现对比，对各种Map进行了分析，讲解了有序集合类型容易混淆的地方，并从源码级别分析了HashMap的基本结构。