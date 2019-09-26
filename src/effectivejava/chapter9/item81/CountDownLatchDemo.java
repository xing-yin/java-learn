package effectivejava.chapter9.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author yinxing
 * @date 2019/9/26
 */

public class CountDownLatchDemo {

    /**
     * Simple framework for timing concurrent execution
     * 同步执行计时的简单框架
     */
    public static long time(Executor executor,
                            int concurrency,
                            Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();
                // tell timer we're ready
                try {
                    start.await();
                    // wait till peers are ready
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                    // tell timer we're done
                }
            });
        }
        ready.await();
        // wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown();
        // and they are off!
        done.await();
        // wait for all workers to finish
        return System.nanoTime() - startNanos;
    }

    /**
     * 注意这个方法使用了三个倒计数锁存器。
     *
     * - 第一个是 ready ，工作线程用它来告诉 timer 线程它们已经准备好了。
     * - 然后工作线程在第二个锁存器 start 上等待。
     * - 当最后一个工作线程调用 ready.countDown 时， timer 线程记录下起始时间，并调用 start.countDown 允许所有的工作线程继续进行。
     * - 然后 timer 线程在第三个锁存器 done 上等待，直到最后一个工作线程运行完该动作，并调用 done.countDown 。
     * 一旦调用这个， timer 线程就会苏醒过来，并记录下结束的时间。
     */

}
