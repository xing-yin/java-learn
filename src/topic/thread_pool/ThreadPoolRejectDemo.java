package topic.thread_pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示：线程池拒绝策略
 *
 * @author Alan Yin
 * @date 2021/11/4
 */

public class ThreadPoolRejectDemo {

    public static void main(String[] args) {

        // testAbortPolicy
//        testRejectedExecutionHandlerPolicy(new ThreadPoolExecutor.AbortPolicy());
//        testRejectedExecutionHandlerPolicy(new ThreadPoolExecutor.DiscardPolicy());
//        testRejectedExecutionHandlerPolicy(new ThreadPoolExecutor.DiscardOldestPolicy());
        testRejectedExecutionHandlerPolicy(new ThreadPoolExecutor.CallerRunsPolicy());

    }

    private static void testRejectedExecutionHandlerPolicy(RejectedExecutionHandler rejectedExecutionHandler) {
        AtomicInteger executeCount = new AtomicInteger(0);
        int corePoolSize = 4;
        int maximumPoolSize = 6;
        long keepAliveTime = 4;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                rejectedExecutionHandler);
        for (int i = 0; i < 100; i++) {
            try {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    executeCount.incrementAndGet();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println(executeCount.get());
    }

}
