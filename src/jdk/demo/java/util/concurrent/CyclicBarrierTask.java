package jdk.demo.java.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示：CyclicBarrier 的使用
 *
 * @author: Alan Yin
 * @date: 2022/1/4
 */
public class CyclicBarrierTask implements Runnable {

    private CyclicBarrier barrier;

    public CyclicBarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        Integer mills = new Random().nextInt(100);
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
            this.barrier.await(); // 线程阻塞
            System.out.println("50 米开跑:" + Thread.currentThread().getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 思考一下，如果数量过大会发生什么？
        int num = 8;
        CyclicBarrier barrier = new CyclicBarrier(num);
        List<CompletableFuture> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CyclicBarrierTask(barrier));
            list.add(future);
        }
        System.out.println("List size: " + list.size());
        for (CompletableFuture future : list) {
            future.get();
        }

        // 可以继续复用
        barrier.reset();
    }
}
