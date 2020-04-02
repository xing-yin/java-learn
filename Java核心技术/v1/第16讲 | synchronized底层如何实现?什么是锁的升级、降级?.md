# 第16讲 | synchronized底层如何实现?什么是锁的升级、降级?

今天我们将深入了解synchronize底层机制，分析其他锁实现和应用场景。

### 问题
synchronized底层如何实现?什么是锁的升级、降级? 

## 典型回答
synchronized代码块是由一对儿monitorenter/monitorexit指令实现的，**Monitor对象是同步的基本实现单元**。 

在Java 6之前，Monitor的实现完全是依靠**操作系统内部的互斥锁**，因为需要进行用户态到内核态的切换，所以同步操作是一个无差别的重量级操作。

(Oracle)JDK中，JVM对此进行了改进，提供了三种不同的Monitor实现，也就是常说的三种不同的锁:**偏斜锁(Biased Locking)、轻量级锁和重量级锁**，大大改进了其性能。

所谓锁的升级、降级，就是JVM优化synchronized运行的机制，**当JVM检测到不同的竞争状况时，会自动切换到适合的锁实现，这种切换就是锁的升级、降级**。

当没有竞争出现时，默认会使用偏斜锁。JVM会利用CAS操作(compare and swap)，在**对象头上的Mark Word**部分设置线程ID，以表示这个对象偏向于当前线程，所以并不涉及真正的互斥锁。这样做的假设是基于在很多应用场景中，大部分对象生命周期中最多会被一个线程锁定，使用偏斜锁可以降低无竞争开销。

如果有另外的线程试图锁定某个已经被偏斜过的对象，JVM就需要**撤销(revoke)**偏斜锁，并切换到轻量级锁实现。轻量级锁依赖CAS操作Mark Word来试图获取锁，如果重试成功，就使用普通的轻量级锁;否则，进一步升级为重量级锁。

据我所知，锁降级确实是会发生的，当JVM进入**安全点(SafePoint)**的时候，会检查是否有闲置的Monitor，然后试图进行降级。

## 考点分析
主要是考察你对Java内置锁实现的掌握，也是并发的经典题目。

> 我个人认为，能够 **基础性地理解这些概念和机制**，其实对于大多数并发编程已经足够了，毕竟大部分工程师未必会进行更底层、更基础的研发，很多时候解决的是知道与否，真正的提高还要靠实践踩坑。

后面我会进一步分析:

* 从源码层面，稍微展开一些synchronized的底层实现。如果你对Java底层源码有兴趣，但还没有找到入手点，这里可以成为一个切入点。

* 理解并发包中java.util.concurrent.lock提供的其他锁实现，毕竟Java可不是只有ReentrantLock一种显式的锁类型，我会结合代码分析其使用。

## 知识扩展
synchronized是JVM内部的Intrinsic Lock，所以偏斜锁、轻量级锁、重量级锁的代码实现，并不在核心类库部分，而是**在JVM的代码中**。 

Java代码运行可能是解释模式也可能是编译模式，所以对应的同步逻辑实现，也会分散在不同模块下，比如，解释器版本就是:

[src/hotspot/share/interpreter/interpreterRuntime.cpp](http://hg.openjdk.java.net/jdk/jdk/file/6659a8f57d78/src/hotspot/share/interpreter/interpreterRuntime.cpp)

为了简化便于理解，我这里会专注于通用的基类实现:

[src/hotspot/share/runtime/](http://hg.openjdk.java.net/jdk/jdk/file/6659a8f57d78/src/hotspot/share/runtime/)

> 另外请注意，链接指向的是最新JDK代码库，所以可能某些实现与历史版本有所不同。

首先，synchronized的行为是JVM runtime的一部分，所以我们需要先找到Runtime相关的功能实现。通过在代码中查询类似“monitor_enter”或“Monitor Enter”，很直观的就可以定位到: 

- sharedRuntime.cpp/hpp，它是解释器和编译器运行时的基类。 

- synchronizer.cpp /hpp，JVM同步相关的各种基础逻辑。

在sharedRuntime.cpp中，下面代码体现了synchronized的主要逻辑。

``` c
Handle h_obj(THREAD, obj);
  if (UseBiasedLocking) {
    // Retry fast entry if bias is revoked to avoid unnecessary inflation
    ObjectSynchronizer::fast_enter(h_obj, lock, true, CHECK);
  } else {
    ObjectSynchronizer::slow_enter(h_obj, lock, CHECK);
  }
```

其实现可以简单进行分解:

- UseBiasedLocking是一个检查，因为，在JVM启动时，我们可以指定是否开启偏斜锁。

偏斜锁并不适合所有应用场景，**撤销操作(revoke)是比较重的行为**，只有当存在较多不会真正竞争的synchronized块儿时，才能体现出明显改善。

> 实践中对于偏斜锁的一直是有 争议的，有人甚至认为，当你需要大量使用并发类库时，往往意味着你不需要偏斜锁。从具体选择来看，我还是建议需要在实践中进行测试，根据结果再决定是否使用。

还有一方面是，偏斜锁会延缓JIT 预热的进程，所以很多性能测试中会显式地关闭偏斜锁，命令如下:

    -XX:-UseBiasedLocking

- `fast_enter`是我们熟悉的完整锁获取路径，`slow_enter`则是绕过偏斜锁，直接进入轻量级锁获取逻辑。

那么fast_enter是如何实现的呢?同样是通过在代码库搜索，我们可以定位到synchronizer.cpp。 类似fast_enter这种实现，解释器或者动态编译器，都是拷贝这段基础逻辑，所以如果我们修改这部分逻辑，**要保证一致性**。

> 这部分代码是非常敏感的，微小的问题都可能导致死锁或者正确性问题。

``` java
void ObjectSynchronizer::fast_enter(Handle obj, BasicLock* lock,
                                	bool attempt_rebias, TRAPS) {
  if (UseBiasedLocking) {
    if (!SafepointSynchronize::is_at_safepoint()) {
      BiasedLocking::Condition cond = BiasedLocking::revoke_and_rebias(obj, attempt_rebias, THREAD);
      if (cond == BiasedLocking::BIAS_REVOKED_AND_REBIASED) {
        return;
      }
	} else {
      assert(!attempt_rebias, "can not rebias toward VM thread");
      BiasedLocking::revoke_at_safepoint(obj);
	}
    assert(!obj->mark()->has_bias_pattern(), "biases should be revoked by now");
  }
 
  slow_enter(obj, lock, THREAD);
}
```

我来分析下这段逻辑实现: 

- biasedLocking定义了偏斜锁相关操作，revoke_and_rebias是获取偏斜锁的入口方法，revoke_at_safepoint则定义了当检测到安全点时的处理逻辑。 
- 如果获取偏斜锁失败，则进入slow_enter。 
- 这个方法里面同样检查是否开启了偏斜锁，但是从代码路径来看，其实如果关闭了偏斜锁，是不会进入这个方法的，所以算是个额外的保障性检查吧。

另外，如果你仔细查看synchronizer.cpp 里，会发现不仅仅是synchronized的逻辑，包括从本地代码，也就是JNI，触发的Monitor动作，全都可以在里面找到 (jni_enter/jni_exit)。

关于biasedLocking的更多细节我就不展开了，明白它是通过CAS设置Mark Word就完全够用了，对象头中Mark Word的结构，可以参考下图:

<div align="center"> <img src="pics/16-1.png" width="500" style="zoom:100%"/> </div><br>
   
顺着锁升降级的过程分析下去，偏斜锁到轻量级锁的过程是如何实现的呢? 我们来看看slow_enter到底做了什么。

``` java
void ObjectSynchronizer::slow_enter(Handle obj, BasicLock* lock, TRAPS) {
  markOop mark = obj->mark();
 if (mark->is_neutral()) {
       // 将目前的 Mark Word 复制到 Displaced Header 上
	lock->set_displaced_header(mark);
	// 利用 CAS 设置对象的 Mark Word
    if (mark == obj()->cas_set_mark((markOop) lock, mark)) {
      TEVENT(slow_enter: release stacklock);
      return;
    }
    // 检查存在竞争
  } else if (mark->has_locker() &&
             THREAD->is_lock_owned((address)mark->locker())) {
	// 清除
    lock->set_displaced_header(NULL);
    return;
  }
 
  // 重置 Displaced Header
  lock->set_displaced_header(markOopDesc::unused_mark());
  ObjectSynchronizer::inflate(THREAD,
                          	obj(),
                              inflate_cause_monitor_enter)->enter(THREAD);
}

```

请结合我在代码中添加的注释，来理解如何从试图获取轻量级锁，逐步进入锁膨胀的过程。你可以发现这个处理逻辑，和我在这一讲最初介绍的过程是十分吻合的。

- 设置Displaced Header，然后利用cas_set_mark设置对象Mark Word，如果成功就成功获取轻量级锁。
- 否则Displaced Header，然后进入锁膨胀阶段，具体实现在infate方法中。

今天就不介绍膨胀的细节了，我这里提供了源代码分析的思路和样例，考虑到应用实践，再进一步增加源代码解读意义不大，有兴趣的同学可以参考我提供的synchronizer.cpp 链 接，例如:

- **defate_idle_monitors是分析锁降级逻辑的入口**，这部分行为还在进行持续改进，因为其逻辑是在安全点内运行，处理不当可能拖长JVM**停顿(STW，stop-the-world)**的时间。
- fast_exit或者slow_exit是对应的锁释放逻辑。

上一讲对比了synchronized和ReentrantLock，Java核心类库中还有其他一些特别的锁类型，具体请参考下面的图。

<div align="center"> <img src="pics/16-2.png" width="500" style="zoom:100%"/> </div><br>

你可能注意到了，这些锁竟然不都是实现了Lock接口，`ReadWriteLock`是一个单独的接口，它通常是代表了一对锁，分别对应**只读和写操作**，标准类库中提供了再入版本的读写 锁实现(ReentrantReadWriteLock)，对应的语义和ReentrantLock比较相似。

StampedLock竟然也是个单独的类型，从类图结构可以看出它是不支持再入性的语义的，也就是**它不是以持有锁的线程为单位**。 

为什么我们需要读写锁(ReadWriteLock)等其他锁呢?

这是因为，虽然ReentrantLock和synchronized简单实用，但是行为上有一定局限性，通俗点说就是“太霸道”，要么不占，要么独占。实际应用场景中，**有的时候不需要大量竞争的写操作，而是以并发读取为主**，如何进一步优化并发操作的粒度呢?

ava并发包提供的读写锁等扩展了锁的能力，它所**基于的原理是多个读操作是不需要互斥的**，因为读操作并不会更改数据，所以不存在互相干扰。而写操作则会导致并发一致性的问 题，所以写线程之间、读写线程之间，需要精心设计的互斥逻辑。

下面是一个基于读写锁实现的数据结构，当数据量较大，并发读多、并发写少的时候，能够比纯同步版本凸显出优势。
 
``` java
public class RWSample {
	private final Map<String, String> m = new TreeMap<>();
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	public String get(String key) {
    	r.lock();
    	System.out.println(" 读锁锁定！");
    	try {
        	return m.get(key);
    	} finally {
        	r.unlock();
    	}
	}

	public String put(String key, String entry) {
    	w.lock();
	System.out.println(" 写锁锁定！");
	    	try {
	        	return m.put(key, entry);
	    	} finally {
	        	w.unlock();
	    	}
		}
	// …
	}
```

在运行过程中**，如果读锁试图锁定时，写锁是被某个线程持有，读锁将无法获得，而只好等待对方操作结束，**这样就可以自动保证不会读取到有争议的数据。 

读写锁看起来比synchronized的粒度似乎细一些，但在实际应用中，其表现也并不尽如人意，主要还是因为**相对比较大的开销**。

所以，JDK在后期引入了StampedLock，在提供类似读写锁的同时，还支持优化读模式。优化读基于假设，大多数情况下读操作并不会和写操作冲突，其逻辑是先试着修改，然后 通过validate方法确认是否进入了写模式，如果没有进入，就成功避免了开销;如果进入，则尝试获取读锁。请参考我下面的样例代码。

``` java
public class StampedSample {
	private final StampedLock sl = new StampedLock();

	void mutate() {
    	long stamp = sl.writeLock();
    	try {
        	write();
    	} finally {
        	sl.unlockWrite(stamp);
    	}
	}

	Data access() {
    	long stamp = sl.tryOptimisticRead();
    	Data data = read();
    	if (!sl.validate(stamp)) {
        	stamp = sl.readLock();
        	try {
            	data = read();
        	} finally {
            	sl.unlockRead(stamp);
        	}
    	}
    	return data;
	}
	// …
}
```

注意，这里的writeLock和unLockWrite一定要保证成对调用。 

你可能很好奇这些显式锁的实现机制，Java并发包内的各种同步工具，不仅仅是各种Lock，其他的如Semaphore、CountDownLatch，甚至是早期的FutureTask 等，都是基于一
种AQS框架。 

今天全面分析了synchronized相关实现和内部运行机制，简单介绍了并发包中提供的其他显式锁，并结合样例代码介绍了其使用方法。

## 一课一练
关于今天我们讨论的你做到心中有数了吗?思考一个问题，你知道“自旋锁”是做什么的吗?它的使用场景是什么? 

> 自旋锁:竞争锁的失败的线程，并不会真实的在操作系统层面挂起等待，而是JVM会让线程做几个空循环(基于预测在不久的将来就能获得)，在经过若干次循环后，如果可以获得锁，那么进入临界区，如果还不能获得锁，才会真实的将线程在操作系统层面进行挂起。

> 适用场景:自旋锁可以减少线程的阻塞，这对于锁竞争不激烈，且占用锁时间非常短的代码块来说，有较大的性能提升，因为自旋的消耗会小于线程阻塞挂起操作的消耗。
如果锁的竞争激烈，或者持有锁的线程需要长时间占用锁执行同步块，就不适合使用自旋锁了，因为自旋锁在获取锁前一直都是占用cpu做无用功，线程自旋的消耗大于线程阻塞挂起操作的消耗，造成cpu的浪费。

# 第17讲 | 一个线程两次调用start()方法会出现什么情况? 

线程是Java并发的基础元素，理解、操纵、诊断线程是Java工程师的必修课，但是你真的掌握线程了吗? 

### 问题
一个线程两次调用start()方法会出现什么情况?谈谈线程的生命周期和状态转移。

## 典型回答

Java的线程是不允许启动两次的，第二次调用必然会抛出IllegalThreadStateException，这是一种运行时异常，多次调用start被认为是编程错误。 

线程生命周期的不同状态，在Java 5以后，线程状态被明确定义在其公共内部枚举类型java.lang.Thread.State中，分别是:

* 新建(NEW)，表示线程**被创建出来还没真正启动**的状态，可以认为它是个Java内部状态。

* 就绪(RUNNABLE)，表示该**线程已经在JVM中执行**，当然由于执行需要计算资源，它可能是正在运行，也可能还在等待系统分配给它CPU片段，**在就绪队列里面排队**。

> 在其他一些分析中，会额外区分一种状态RUNNING，但是从Java API的角度，并不能表示出来。

* 阻塞(BLOCKED)，这个状态和同步非常相关，**阻塞表示线程在等待Monitor lock**。

> 比如，线程试图通过synchronized去获取某个锁，但是其他线程已经独占了，那么当前线程就会处于阻塞状态。

* 等待(WAITING)，表示**正在等待其他线程采取某些操作**。

> 一个常见的场景是类似生产者消费者模式，发现任务条件尚未满足，就让当前消费者线程等待(wait)，另外的生产者线程去准备任务数据，然后通过类似notify等动作，通知消费线程可以继续工作了。Thread.join()也会令线程进入等待状态。

* 计时等待(TIMED_WAIT)，**进入条件和等待状态类似，但是调用的是存在超时条件**的方法，比如wait或join等方法的指定超时版本，如下面示例:

		public final native void wait(long timeout) throws InterruptedException;

* 终止(TERMINATED)，不管是**意外退出还是正常执行结束，线程已经完成使命，终止运行**，也有人把这个状态叫作死亡。 在第二次调用start()方法的时候，线程可能处于终止或者其他(非NEW)状态，但是不论如何，都是不可以再次启动的。

## 考点分析
下面分析会对比一个状态图进行介绍。总的来说，理解线程对于我们`日常开发或者诊断分析`，都是不可或缺的基础。

面试官可能会以此为契机，从各种不同角度考察你对线程的掌握: 

- 相对理论一些的面试官可以会问你线程到底是什么以及Java底层实现方式。 

- 线程状态的切换，以及和锁等并发工具类的互动。 

- 线程编程时容易踩的坑与建议等。
可以看出，仅仅是一个线程，就有非常多的内容需要掌握。我们选择重点内容，开始进入详细分析。

## 知识扩展
### 首先，我们来整体看一下线程是什么?

从操作系统的角度，可以简单认为，线程是系统调度的最小单元，一个进程可以包含多个线程，作为任务的真正运作者，有自己的栈(Stack)、寄存器(Register)、本地存储 (Thread Local)等，但是会和进程内其他线程共享文件描述符、虚拟地址空间等。

在具体实现中，线程还分为内核线程、用户线程，**Java的线程实现是与虚拟机相关的**。

> 对于我们最熟悉的Sun/Oracle JDK，其线程也经历了一个演进过程，基本上在Java 1.2之后，JDK已经抛弃了所谓的Green Thread，也就是用户调度的线程，现在的模型是**一对一映射到操作系统内核线程**。

如果我们来看Thread的源码，你会发现其基本操作逻辑大都是以JNI形式调用的本地代码。

``` java
private native void sart0();
private native void setPriority0(int newPriority); 
private native void interrupt0();
```

这种实现有利有弊，总体上来说，Java语言得益于**精细粒度的线程和相关的并发操作**，其构建高扩展性的大型应用的能力已经毋庸置疑。但是，其复杂性也提高了并发编程的门槛， 近几年的Go语言等提供了**协程**(coroutine)，大大提高了构建并发应用的效率。

> 与此同时，Java也在Loom项目中，孕育新的类似轻量级用户线程(Fiber)等机制，也许在不久的 将来就可以在新版JDK中使用到它。

下面，我来分析下线程的基本操作。如何创建线程想必你已经非常熟悉了，请看下面的例子:

``` java
Runnable task = () -> {System.out.println("Hello World!");};
Thread myThread = new Thread(task);
myThread.start();
myThread.join();
```

我们可以直接扩展Thread类，然后实例化。但在本例中，我选取了另外一种方式，就是实现一个Runnable，将代码逻放在Runnable中，然后构建Thread并启动(start)，等待结束(join)。

**Runnable的好处是，不会受Java不支持类多继承的限制，重用代码实现，当我们需要重复执行相应逻辑时优点明显**。而且，也能更好的与现代Java并发库中的Executor之类框架结合使用，比如将上面start和join的逻辑完全写成下面的结构:
    
``` java
Future future = Executors.newFixedThreadPool(1)
.submit(task)
.get();
```
这样我们就不用操心线程的创建和管理，也能利用Future等机制更好地处理执行结果。**线程生命周期通常和业务之间没有本质联系**，混淆实现需求和业务需求，就会降低开发的效率。

从线程生命周期的状态开始展开，那么在Java编程中，有哪些因素可能影响线程的状态呢?主要有: 

- 线程自身的方法，除了`start`，还有多个`join`方法，等待线程结束;`yield`是告诉调度器，主动让出CPU;另外，就是一些已经被标记为过时的resume、stop、suspend之类。

> 据我所知，在JDK最新版本中，destory/stop方法将被直接移除。 

- 基类Object提供了一些**基础的wait/notify/notifyAll方法**。如果我们持有某个对象的Monitor锁，调用wait会让当前线程处于等待状态，直到其他线程notify或者notifyAll。所以，本质上是提供了Monitor的获取和释放的能力，是基本的线程间通信方式。

- **并发类库中的工具**，比如CountDownLatch.await()会让当前线程进入等待状态，直到latch被基数为0，这可以看作是线程间通信的Signal。 我这里画了一个状态和方法之间的对应图:

<div align="center"> <img src="pics/17-1.png" width="500" style="zoom:100%"/> </div><br>

> Thread和Object的方法，听起来简单，但是实际应用中被证明非常晦涩、易错，这也是为什么Java后来又引入了并发包。总的来说，有了并发包，大多数情况下，我们已经不再需要去调用wait/notify之类的方法了。

### 前面谈了不少理论，下面谈谈线程API使用，我会侧重于平时工作学习中，容易被忽略的一些方面。

先来看看**守护线程(Daemon Thread)**，有的时候应用中需要一个长期驻留的服务程序，但是不希望其影响应用退出，就可以将其设置为守护线程，如果JVM发现只有守护线程存在时，将结束进程，具体可以参考下面代码段。**注意，必须在线程启动之前设置**。

``` java
Thread daemonThread = new Thread();
daemonThread.setDaemon(true);
daemonThread.start();
```

再来看看Spurious wakeup。尤其是在多核CPU的系统中，线程等待存在一种可能，就是在没有任何线程广播或者发出信号的情况下，线程就被唤醒，如果处理不当就可能出现诡异的并发问题，所以我们在等待条件过程中，**建议采用下面模式来书写**。

``` java
// 推荐
while ( isCondition()) {
waitForAConfition(...);
}

// 不推荐，可能引入 bug
if ( isCondition()) {
waitForAConfition(...);
}
``` 

Thread.onSpinWait()，这是Java 9中引入的特性。我在专栏第16讲给你留的思考题中，提到“自旋锁”(spin-wait, busy-waiting)，也可以认为其不算是一种锁，而是一种**针对短期等待的性能优化技术**。“onSpinWait()”没有任何行为上的保证，而是对JVM的一个暗示，JVM可能会利用CPU的pause指令进一步提高性能，性能特别敏感的应用可以关注。

再有就是**慎用ThreadLocal**，这是Java提供的一种保存线程私有信息的机制，因为其在整个线程生命周期内有效，所以可以方便地在一个线程关联的不同业务模块之间传递信息，比 如事务ID、Cookie等上下文相关信息。

它的实现结构，可以参考源码，数据存储于线程相关的ThreadLocalMap，其内部条目是弱引用，如下面片段。
   
``` java
static class ThreadLocalMap {
	static class Entry extends WeakReference<ThreadLocal<?>> {
    	/** The value associated with this ThreadLocal. */
    	Object value;
    	Entry(ThreadLocal<?> k, Object v) {
        	super(k);
    	value = v;
    	}
      }
   // …
}
```

当Key为null时，该条目就变成“废弃条目”，相关“value”的回收，往往依赖于几个关键点，即set、remove、rehash。

下面是set的示例，我进行了精简和注释:

``` java
private void set(ThreadLocal<?> key, Object value) {
	Entry[] tab = table;
	int len = tab.length;
	int i = key.threadLocalHashCode & (len-1);

	for (Entry e = tab[i];; …) {
    	//…
    	if (k == null) {
// 替换废弃条目
        	replaceStaleEntry(key, value, i);
        	return;
    	}
       }

	tab[i] = new Entry(key, value);
	int sz = ++size;
//  扫描并清理发现的废弃条目，并检查容量是否超限
	if (!cleanSomeSlots(i, sz) && sz >= threshold)
    	rehash();// 清理废弃条目，如果仍然超限，则扩容（加倍）
}  
```

具体的清理逻辑是实现在cleanSomeSlots和expungeStaleEntry之中，如果你有兴趣可以自行阅读。 

结合专栏第4讲介绍的引用类型，我们会发现一个特别的地方，通常弱引用都会和引用队列配合清理机制使用，但是ThreadLocal是个例外，它并没有这么做。
这意味着，废弃项目的回收依赖于显式地触发，否则就要等待线程结束，进而回收相应ThreadLocalMap!这就是很多OOM的来源，所以**通常都会建议，应用一定要自己负责remove，并且不要和线程池配合，因为worker线程往往是不会退出的**。

今天，我介绍了线程基础，分析了生命周期中的状态和各种方法之间的对应关系，这也有助于我们更好地理解synchronized和锁的影响，并介绍了一些需要注意的操作。

## 一课一练
关于今天我们讨论的题目你做到心中有数了吗?今天我准备了一个有意思的问题，写一个最简单的打印HelloWorld的程序，说说看，运行这个应用，Java至少会创建几个线程呢? 然后思考一下，如何明确验证你的结论，真实情况很可能令你大跌眼镜哦。

