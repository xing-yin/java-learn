package jdk.demo.java.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 演示： CompletableFuture 使用
 *
 * @author: Alan Yin
 * @date: 2022/1/5
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello,YX");
        cf.thenApply(String::toLowerCase);
        System.out.println(cf.get());
        // 需要重新创建一个 CompletionStage
        cf.thenCompose(s -> CompletableFuture.supplyAsync(s::toLowerCase));
        System.out.println(cf.get());
    }
}
