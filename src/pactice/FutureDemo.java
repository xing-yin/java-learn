package pactice;

import java.util.concurrent.*;

/**
 * @author: Alan Yin
 * @date: 2021/12/8
 */
public class FutureDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Callable<Integer> task = new RandomSleepTask();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<Integer> future1 = executor.submit(task);
        Future<Integer> future2 = executor.submit(task);
        // 等待执行结果
        Integer result1 = future1.get(1, TimeUnit.SECONDS);
        Integer result2 = future2.get(1, TimeUnit.SECONDS);
        System.out.println("future1.isCancelled:" + future1.isCancelled());
        System.out.println("future1.isDone:" + future1.isDone());
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
    }
}
