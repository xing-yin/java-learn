package javaCourse.concurrency.conc2.threadpool;

import java.util.concurrent.*;

/**
 * @author Alan Yin
 * @date 2021/12/2
 */

public class ExecutorServiceDemo {

    public static void main(String[] args) {
        ExecutorService executorService;
        // 1.演示 newSingleThreadScheduledExecutor
//        executorService = Executors.newSingleThreadScheduledExecutor();

        // 2.演示 newSingleThreadScheduledExecutor
//        executorService = Executors.newFixedThreadPool(8);

        // 3.演示 newCachedThreadPool
//        executorService = Executors.newCachedThreadPool();

        // 4.演示 newCachedThreadPool
        executorService = Executors.newScheduledThreadPool(8);

//        processBiz(executorService);
//        System.out.println("Main Thread end");

        // ==========================================================
        // 演示 submit 方法
        submitDemo();
    }

    /**
     * submit() 与 execute() 区别：前者会返回 Future<T> 对象，可以做一些处理，后者无返回值
     */
    private static void submitDemo() {
        ExecutorService executorService = Executors.newScheduledThreadPool(8);
        try {
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "I am a task,which submited by the so called,and return by those anoymous workers";
                }
            });
            String str = future.get();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void processBiz(ExecutorService executorService) {
        for (int i = 0; i < 100; i++) {
            int num = i;
            executorService.execute(() -> {
                try {
                    System.out.println("begin:" + num);
                    Thread.sleep(100);
                    System.out.println("end:" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
