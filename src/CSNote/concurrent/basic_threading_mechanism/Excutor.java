package CSNote.concurrent.basic_threading_mechanism;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
 *
 * @author yinxing
 * @date 2019/2/19
 */

public class Excutor {


    public static void main(String[] args) {

//        /**
//         * 主要有三种 Executor：
//         * CachedThreadPool：一个任务创建一个线程；
//         * FixedThreadPool：所有任务只能使用固定大小的线程；
//         * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
//         */
//        ExecutorService executorService = Executors.newCachedThreadPool();
////        ExecutorService executorService = Executors.newFixedThreadPool(5);
////        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new MyRunnable());
//        }
//        executorService.shutdown();

        /**
         * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，
         * 则相当于调用每个线程的 interrupt() 方法。
         * 以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        System.out.println("Main run");

        /**
         * 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，
         * 它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
         */
        Future<?> future = executorService.submit(() -> {
            //...
        });
        future.cancel(true);
    }
}
