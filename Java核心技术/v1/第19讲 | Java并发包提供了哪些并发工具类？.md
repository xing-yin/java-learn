第19讲 | Java并发包提供了哪些并发工具类？
====

### 问题
Java并发包提供了哪些并发工具类?

## 典型回答
通常说的并发包就是java.util.concurrent及其子包，集中了Java并发的各种基础工具类，具体主要包括几个方面: 

- 提供了比synchronized**更加高级的各种同步结构**，包括CountDownLatch、CyclicBarrier、Semaphore等，可以实现更加丰富的多线程操作，比如利用Semaphore作为资源控制器，限制同时进行工作的线程数量。 
- 各种**线程安全的容器**，比如最常见的ConcurrentHashMap、有序的ConcunrrentSkipListMap，或者通过类似快照机制，实现线程安全的动态数组CopyOnWriteArrayList等。
- 各种**并发队列实现**，如各种BlockedQueue实现，比较典型的ArrayBlockingQueue、 SynchorousQueue或针对特定场景的PriorityBlockingQueue等。 
- 强大的**Executor框架**，可以创建各种不同类型的线程池，调度任务运行等，绝大部分情况下，不再需要自己从头实现线程池和任务调度器。

## 考点分析
进行多线程编程，无非是达到几个目的:

*  利用多线程提高程序的**扩展能力**，以达到业务对**吞吐量**的要求。
*  协调**线程间调度、交互**，以完成业务逻辑。
*  **线程间传递数据和状态**，这也是实现业务逻辑的需要。

> 往往面试官会进一步考察如何利用并发包实现某个特定的用例，分析实现的优缺点等。

如果这方面的基础比较薄弱，我的建议是:

*  从总体上，把握住几个主要组成部分(前面回答)
*  理解具体设计、实现和能力
*  再深入掌握一些比较典型工具类的适用场景、用法甚至是原理，并熟练写出典型的代码用例

> 掌握这些通常就够用了，毕竟并发包提供了方方面面的工具，很少有机会能在应用中全面使用过，扎实地掌握核心功能就非常不错了。真正特别深入的经验，还是得靠在实际场景中踩坑来获得。

## 知识扩展
### 首先，看一下并发包里丰富的同步结构

前几讲已分析过各种不同的显式锁，本节将专注于:

- CountDownLatch，允许**一个或多个线程等待某些操作完成**
- CyclicBarrier，一种辅助性的同步结构，**允许多个线程等待到达某个屏障**
- Semaphore，**Java版本的信号量实现**

Java提供了经典信号量(Semaphore))的实现，它通过**控制一定数量的允许(permit)的方式，来达到限制通用资源访问的目的** 。

> 【信号量类比】
> 
> 你可以想象一下在机场等出租车时，当很多空出租车就位时，为防止过度拥挤，调度员指挥排队等待坐车的队伍一次进来5个人上车，等这5个人坐车出发，再放进去下一批，这和Semaphore的工作原理有些类似。

试试使用Semaphore来模拟实现这个调度过程:

```
public class UseSemaphoreSample {

    public static void main(String[] args) {
        System.out.println("Action go!");
        Semaphore semaphore = new Semaphore(5);
        for (int i =0; i < 10; i++) {
            Thread t = new Thread(new SemaphoreWorker(semaphore));
            t.start();
        }
    }

    static class SemaphoreWorker implements Runnable {
        private String name;
        private Semaphore semaphore;

        public SemaphoreWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                System.out.println(" is waiting for a permit!");
                semaphore.acquire();
                System.out.println(" acquire a permit !");
                System.out.println("executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("released a permit!");
                semaphore.release();
            }
        }

        private void log(String msg) {
            if (name == null) {
                name = Thread.currentThread().getName();
            }
            System.out.println(name + " " + msg);
        }
    }
}
```

这段代码是比较典型的Semaphore示例，其逻辑是，**线程试图获得工作允许，得到许可则进行任务，然后释放许可，这时等待许可的其他线程，就可获得许可进入工作状态**，直到全部处理结束。编译运行，我们就能看到Semaphore的允许机制对工作线程的限制。

> 但是，从具体节奏来看，其实并不符合我们前面场景的需求，因为本例中Semaphore的用法实际是保证，一直有5个人可以试图乘车，如果有1个人出发了，立即就有排队的人获得 许可，而这并不完全符合我们前面的要求。

再修改一下，演示个非典型的Semaphore用法。

```
public class NonNormalUseSemaphoreSample {


    static class MyWorker implements Runnable {
        private String name;
        private Semaphore semaphore;

        public MyWorker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Executed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i =0; i < 10; i++) {
            Thread t = new Thread(new MyWorker(semaphore));
            t.start();
        }
        System.out.println("Action go");
        semaphore.release(5);
        System.out.println("waiting for permit off");
        while (semaphore.availablePermits()!=0 ) {
            Thread.sleep(100L);
        }
        System.out.println("action go again");
        semaphore.release(5);
    }
}
```

> 注意，上面的代码，更侧重的是演示`Semaphore的功能以及局限性`，其实有很多线程编程中的反实践，比如使用了sleep来协调任务执行，而且使用轮询调用availalePermits来检测信号量获取情况，这都是很低效并且脆弱的，通常只是用在测试或者诊断场景。

总的来说，**Semaphore就是个计数器，其基本逻辑基于acquire/release，并没有太复杂的同步逻辑**。 

> 如果Semaphore的数值被初始化为1，那么一个线程就可以通过acquire进入互斥状态，本质上和互斥锁是非常相似的。
> 
> 但是区别也非常明显，比如互斥锁是有持有者的，而对于Semaphore这种计数器结构，虽然有类似功能，但其实不存在真正意义的持有者，除非我们进行扩展包装。 

#### 下面，来看看CountDownLatch和CyclicBarrier，它们的行为有一定的相似度，经常会被考察二者有什么区别。

- CountDownLatch是**不可以重置，所以无法重用**;  CyclicBarrier则没有这种限制，可以重用。 

- CountDownLatch的基本操作组合是countDown/await。调用await的线程阻塞等待countDown足够的次数，**不管在一个线程还是多个线程里countDown，只要次数足够即可**【所以很适合事件触发的场景】。**CountDownLatch操作的是事件**。

- CyclicBarrier的基本操作组合，则就是`await`，当所有的伙伴(parties)都调用了await，才会继续进行任务，并自动进行重置。

> 注意，正常情况下，CyclicBarrier的重置都是自动发生的，如果我们调用reset方法，但还有线程在等待，就会导致等待线程被打扰，抛出BrokenBarrierException异常。 **CyclicBarrier侧重点是线程，而不是调用事件，它的典型应用场景是用来等待并发线程结束。**

如果用CountDownLatch去实现上面的排队场景，该怎么做呢? 假设有10个人排队，我们将其分成5个人一批，通过CountDownLatch来协调批次，试试下面的示例代码。

```
public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWorker(countDownLatch));
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWorker(countDownLatch));
            t.start();
        }

        // 注意这里是为了演示的目的逻辑，不是推荐的协调方式
        while (countDownLatch.getCount() != 1) {
            Thread.sleep(100L);
        }
        System.out.println("wait for first batch finish");
        countDownLatch.countDown();
    }

    /**
     * 第一个批工作器
     */
    static class FirstBatchWorker implements Runnable {
        private CountDownLatch latch;

        public FirstBatchWorker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("first batch executed!");
            latch.countDown();
        }
    }

    static class SecondBatchWorker implements Runnable {
        private CountDownLatch latch;

        public SecondBatchWorker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
                System.out.println("second batch executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
```

CountDownLatch的调度方式相对简单，**后一批次的线程进行await，等待前一批countDown足够多次**。这个例子也从侧面体现出了它的局限性，虽然它也能够支持10个人排队的 情况，但是因为不能重用，如果要支持更多人排队，就不能依赖一个CountDownLatch进行了。其编译运行输出如下:

<div align="center"> <img src="pics/19-1.png" width="500" style="zoom:100%"/> </div><br>
﻿﻿
实际应用中的条件依赖，往往没有这么别扭，**CountDownLatch用于线程间等待操作结束是非常简单普遍的用法**。

> 通过 `countDown/await` 组合进行通信是很高效的，通常不建议使用例子里那个循环等待方式。

#### 如果用CyclicBarrier来表达这个场景呢?

CyclicBarrier其实反映的是线程并行运行时的协调，在下面的示例里，从逻辑上，5个工作线程其实更像是代表了5个可以就绪的 空车，而不再是5个乘客，对比前面CountDownLatch的例子更有助于我们区别它们的抽象模型，请看下面的示例代码:

``` java
public class CyclicBarrierSample {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action go again!");
            }
        });

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
    }

    static class CyclicWorker implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public CyclicWorker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("executed");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

为了让输出更能表达运行时序，使用了CyclicBarrier特有的barrierAction，当屏障被触发时，Java会自动调度该动作。因为**CyclicBarrier会自动进行重置**，所以这个逻辑其实可以非常自然的支持更多排队人数。其编译输出如下:

<div align="center"> <img src="pics/19-2.png" width="500" style="zoom:100%"/> </div><br>

并发类库还提供了`Phaser`，功能与CountDownLatch很接近，但是**它允许线程动态地注册到Phaser上面**，而CountDownLatch显然是不能动态设置的。Phaser的设计初衷是，实现多个线程类似步骤、阶段场景的协调，线程注册等待屏障条件触发，进而协调彼此间行动，具体请[参考这个例子](https://www.baeldung.com/java-phaser)。

#### 接下来，梳理下并发包里提供的线程安全Map、List和Set
首先，请参考下面的类图。

<div align="center"> <img src="pics/19-3.png" width="500" style="zoom:100%"/> </div><br>
﻿﻿
> 可以看出来，总体上种类和结构还是比较简单的。
> 
> 如果我们的应用侧重于Map放入或者获取的速度，而不在乎顺序，大多推荐使用ConcurrentHashMap。
> 
> 反之则使用ConcurrentSkipListMap; 如果我们需要对大量数据进行非常频繁地修改，ConcurrentSkipListMap （跳表）也可能表现出优势。

普通无顺序场景选择HashMap，有顺序场景则可以选择类似TreeMap，但是为什么并发容器里面没有 ConcurrentTreeMap 呢?

> 这是因为TreeMap 要实现高效的线程安全是非常困难的，它的实现基于复杂的红黑树。
> 
> 为保证访问效率，当我们插入或删除节点时，会移动节点进行平衡操作，这导致在并发场景中难以进行合理粒度的同步。
> 
> SkipList结构则要相对简单很多，通过层次结构提高访问速度，虽然不够紧凑，空间使用有一定提高(O(nlogn))，但是在增删元素时线程安全的开销要好很多。SkipList的内部结构见示意图。

<div align="center"> <img src="pics/19-4.png" width="500" style="zoom:100%"/> </div><br>

关于两个CopyOnWrite容器，CopyOnWriteArraySet是通过包装了CopyOnWriteArrayList来实现的，我们可以专注于理解一种。

首先，CopyOnWrite到底是什么意思呢?

它的原理是:**任何修改操作（如add、set、remove），都会拷贝原数组，修改后替换原来的数组**，通过这种防御性的方式，实现另类的线程安全。请看下面的代码片段，进行注释的地方，可以清晰地理解其逻辑。

```
public boolean add(E e) {
	synchronized (lock) {
    	Object[] elements = getArray();
    	int len = elements.length;
           // 拷贝
    	Object[] newElements = Arrays.copyOf(elements, len + 1);
    	newElements[len] = e;
           // 替换
    	setArray(newElements);
    	return true;
            }
}
final void setArray(Object[] a) {
	array = a;
}
```
所以这种数据结构，**相对比较适合读多写少的操作，不然修改的开销还是非常明显的**。

本节对Java并发包进行了总结，并且结合实例分析了各种同步结构和部分线程安全容器，希望对你有所帮助。

## 一课一练
思考题:你使用过类似CountDownLatch的同步结构解决实际问题吗?谈谈你的使用场景和心得。 

``` 参考示例
需求是每个对象一个线程，分别在每个线程里计算各自的数据，最终等到所有线程计算完毕，我还需要将每个有共通的对象进行合并，所以用它很合适。
```

#### 几个并发类对比
- 1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
	
	- CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
	- CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
	- 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

- 2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。