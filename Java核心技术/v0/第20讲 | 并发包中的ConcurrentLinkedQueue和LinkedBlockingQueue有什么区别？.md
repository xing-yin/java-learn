第20讲 | 并发包中的ConcurrentLinkedQueue和LinkedBlockingQueue有什么区别？
=====

本节介绍一下线程安全队列。Java标准库提供了非常多的线程安全队列，很容易混淆。 

### 问题
并发包中的ConcurrentLinkedQueue和LinkedBlockingQueue有什么区别?

## 典型回答
> 严格来讲，类似ConcurrentLinkedQueue这种 “Concurrent**” 容器，才是真正代表并发。 

问题中它们的区别:

- **Concurrent类型基于lock-free**，在常见的多线程访问场景，一般可以提供**较高吞吐量**。
- LinkedBlockingQueue 内部则是**基于锁**，并提供了BlockingQueue的**等待性方法**。 

不知道你有没有注意到，java.util.concurrent包提供的容器(Queue、List、Set)、Map，从命名上可以大概区分为`Concurrent**`、`CopyOnWrite`和`Blocking**`等三类，同样是线程安全容器，可以简单认为: 

- Concurrent类型没有类似CopyOnWrite之类容器**相对较重的修改开销**。
- 凡事都有代价，**Concurrent往往提供了较低的遍历一致性**。

> 你可以这样理解所谓的弱一致性，例如，当利用迭代器遍历时，如果容器发生修改，迭代器仍然可以继续进行遍历。

- 与弱一致性对应的，就是同步容器常见的行为**“fast-fail”**，也就是检测到容器在遍历过程中发生了修改，则**抛出ConcurrentModifcationException，不再继续遍历**。 
- 弱一致性的另外一个体现是，**size等操作准确性是有限的**，未必是100%准确。同时，读取的性能具有一定的**不确定性**。

## 考点分析
本节问题考察你是否了解并发包内部不同容器实现的设计目的和实现区别。 

日常开发中很多线程间数据传递都要依赖于队列，Executor框架提供的各种线程池，同样无法离开队列。面试官可以从不同角度考察，比如:

- 哪些队列是**有界/无界**?
- 针对特定场景需求，如何选择**合适的队列**实现?
- 从源码的角度，常见的线程安全队列是如何实现的，并进行了哪些改进以提高性能表现?

## 知识扩展 
### 线程安全队列一览
常见的集合中如LinkedList是个Deque，只不过不是线程安全的。下图是Java并发类库提供的各种各样的线程安全队列实现，注意，图中并未将非线程安全部分包含进来。

<div align="center"> <img src="pics/20-1.png" width="500" style="zoom:120%"/> </div><br>

我们可以尝试从不同的角度分个类，从基本的数据结构的角度分析，有两个特别的Deque实现，ConcurrentLinkedDeque和LinkedBlockingDeque。**Deque的侧重点是支持对队列头尾都进行插入和删除**，所以提供了特定的方法，如:

- 尾部插入时需要的addLast(e)、oferLast(e)。
- 尾部删除所需要的removeLast()、pollLast()。 

> 从上面这些角度，能够理解ConcurrentLinkedDeque和LinkedBlockingQueue的主要功能区别，也就足够日常开发的需要了。但是如果我们深入一些，通常会更加关注下面这些方面。

从行为特征来看，绝大部分Queue都实现了BlockingQueue接口。在常规队列操作基础上，**Blocking意味着其提供了特定的等待性操作，获取时(take)等待元素进队，或者插入时(put)等待队列出现空位**。

```
 /**
 * 获取并移除队列头结点，如果必要，其会等待直到队列出现元素(这个过程会出现阻塞)
 */
E take() throws InterruptedException;

/**
 * 插入元素，如果队列已满，则等待直到队列出现空闲空间
 */
void put(E e) throws InterruptedException;  

```

另一个BlockingQueue经常被考察的点，就是**是否有界(Bounded、Unbounded)**，这点往往会影响在应用开发中的选择。 

- ArrayBlockingQueue是最典型的的有界队列，其内部以fnal的数组保存数据，数组的大小就决定了队列的边界，所以我们在创建ArrayBlockingQueue时，都要指定容量，如

		public ArrayBlockingQueue(int capacity, boolean fair)
     
- LinkedBlockingQueue，易被误解为无边界，但其行为和内部代码都是基于有界的逻辑实现的，只不过如果在创建时没有指定容量，那么其容量限制就自动被设置为`Integer.MAX_VALUE`，成为了无界队列。

- SynchronousQueue，这是一个奇葩的队列实现，**每个删除操作都要等待插入操作，反之每个插入操作也都要等待删除动作**。那么这个队列的容量是多少?是1吗?其实不是的，其内部容量是0。

- PriorityBlockingQueue是无边界的优先队列，虽然严格意义上来讲，其大小总归是要受系统资源影响。

- DelayedQueue和LinkedTransferQueue 同样是无边界的队列。

**对于无边界的队列，有一个自然的结果，就是put操作永远也不会发生其他BlockingQueue的那种等待情况**。 

如果我们分析不同队列的底层实现，BlockingQueue基本都是基于锁实现，一起来看看典型的LinkedBlockingQueue。

```
	/** Lock held by take, poll, etc */
    private final ReentrantLock takeLock = new ReentrantLock();

    /** Wait queue for waiting takes */
    private final Condition notEmpty = takeLock.newCondition();

    /** Lock held by put, offer, etc */
    private final ReentrantLock putLock = new ReentrantLock();

    /** Wait queue for waiting puts */
    private final Condition notFull = putLock.newCondition();
```

> 在介绍 ReentrantLock 的条件变量用法的时候分析过 ArrayBlockingQueue ，不知道你有没有注意到，其条件变量与 LinkedBlockingQueue 版本的实现是有区别的。notEmpty、notFull 都是同一个再入锁的条件变量，而**LinkedBlockingQueue则改进了锁操作的粒度，头、尾操作使用不同的锁，所以在通用场景下，它的吞吐量相对要更好一些**。

下面的take方法与ArrayBlockingQueue中的实现，也是有不同的，由于其内部结构是链表，需要自己维护元素数量值，请参考下面的代码。

```
public E take() throws InterruptedException {
    final E x;
    final int c;
    final AtomicInteger threadUnSafeCount = this.threadUnSafeCount;
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lockInterruptibly();
    try {
    	 // 链表为空，则等待
        while (threadUnSafeCount.get() == 0) {
            notEmpty.await();
        }
        // x 是出队的元素
        x = dequeue();
        c = threadUnSafeCount.getAndDecrement();
        if (c > 1)
            notEmpty.signal();
    } finally {
        takeLock.unlock();
    }
    if (c == capacity)
        signalNotFull();
    return x;
}
```

类似ConcurrentLinkedQueue等，则是**基于CAS的无锁技术**，不需要在每个操作时使用锁，所以扩展性表现要更加优异。 

相对比较另类的SynchronousQueue，在Java 6中，其实现发生了非常大的变化，**利用CAS替换掉了原本基于锁的逻辑，同步开销比较小**。它是Executors.newCachedThreadPool()的默认队列。 

### 队列使用场景与典型用例
实际开发中，**Queue被广泛使用在生产者-消费者场景**。

比如利用BlockingQueue来实现，由于其提供的等待机制，我们可以少操心很多协调工作，参考下面样例代码:

```
public class ConsumerProducer {

    public static final String EXIT_MSG = "Good bye!";

    public static void main(String[] args) {
        // 使用较小的队列，便于展示影响
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(6);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(5L);
                    String msg = "Message" + i;
                    System.out.println("Produced new item:" + msg);
                    blockingQueue.put(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                System.out.println("Time to say good bye");
                blockingQueue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                String msg;
                // 如果不是退出标识的字符串，就一直消费
                while (!EXIT_MSG.equalsIgnoreCase((msg = blockingQueue.take()))) {
                    System.out.println("Consumed item :" + msg);
                    Thread.sleep(10L);
                }
                System.out.println("Got exit message,bye!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

> 上面是一个典型的生产者-消费者样例，如果使用非Blocking的队列，那么我们就要自己去实现轮询、条件判断(如检查poll返回值是否null)等逻辑，如果没有特别的场景要求，Blocking实现起来代码更加简单、直观。

### 前面介绍了各种队列实现，在日常的应用开发中，如何进行选择呢? 
以LinkedBlockingQueue、ArrayBlockingQueue和SynchronousQueue为例，一起来分析一下，根据需求可以从很多方面考量:

- 考虑对**队列边界**的要求。ArrayBlockingQueue是有明确的容量限制的，而LinkedBlockingQueue则取决于我们是否在创建时指定，SynchronousQueue则不能缓存任何元素。

- **空间利用角度**，数组结构的ArrayBlockingQueue要比LinkedBlockingQueue紧凑，因为其不需要创建所谓节点，但是其初始分配阶段就需要一段连续的空间，所以初始内存需求更大。

- 通用场景中，LinkedBlockingQueue的**吞吐量**一般优于ArrayBlockingQueue，因为它实现了更加细粒度的锁操作。ArrayBlockingQueue**实现比较简单，性能更好预测**，属于表现稳定的“选手”。

- 如果我们需要实现的是两个线程之间**接力性(handof)的场景**，可能会选择CountDownLatch，SynchronousQueue也符合这种场景的，而且线程间协调和数据传输统一起来，代码更加规范。

- 令人意外的是，很多时候SynchronousQueue的性能表现，往往大大超过其他实现，尤其是在队列元素较小的场景。 

本节分析了Java中各种线程安全队列，试图从几个角度，让每个队列的特点更加明确，进而希望减少你在日常工作中使用时的困扰。

## 一课一练
思考题是，指定某种结构，比如栈，用它实现一个BlockingQueue，实现思路是怎样的呢?