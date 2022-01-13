package jdk.demo.java.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 演示：CountDownLatch 的使用
 *
 * @author: Alan Yin
 * @date: 2022/1/4
 */
public class CountDownLatchTask implements Runnable {

    private CountDownLatch latch;

    public CountDownLatchTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        Integer mills = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
            this.latch.countDown();
            System.out.println("任务已 ok:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int num = 100;
        CountDownLatch latch = new CountDownLatch(num);
        List<CompletableFuture> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CountDownLatchTask(latch));
            list.add(future);
        }

        latch.await();
        System.out.println("list.size() :" + list.size());
        for (CompletableFuture future : list) {
            future.get();
        }
    }
}
