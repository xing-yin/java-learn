package CSNote.concurrent.mutually_exclusive_synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized
 *
 * @author yinxing
 * @date 2019/2/20
 */

public class SynchronizedExample {

    // 1. 同步一个代码块
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 2. 同步一个方法
    public synchronized void func2() {
        // ...
    }

    // 3.同步一个类
    // 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    // 4. 同步一个静态方法
    // 作用于整个类
    public synchronized static void func4(){
        // ...
    }

    public static void main(String[] args) {
        /**
         * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
         *
         对于以下代码，使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
         */
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        SynchronizedExample se1 = new SynchronizedExample();
//        executorService.execute(() -> se1.func1());
//        executorService.execute(() -> se1.func1());

        /**
         * 对于以下代码，两个线程调用了不同对象的同步代码块，因此这两个线程就不需要同步。从输出结果可以看出，两个线程可能交叉执行。
         */
//        SynchronizedExample se2 = new SynchronizedExample();
//        SynchronizedExample se3 = new SynchronizedExample();
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
//        executorService2.execute(() -> se2.func1());
//        executorService2.execute(() -> se3.func1());

        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        executorService3.execute(()->e1.func3());
        executorService3.execute(()->e2.func3());


    }


}
