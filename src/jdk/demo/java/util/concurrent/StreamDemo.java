package jdk.demo.java.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: Alan Yin
 * @date: 2022/1/12
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 10000).forEach(i -> list.add(i));
        List<Long> longList = list.stream().parallel()
                .map(i -> i.longValue())
                .sorted()
                .collect(Collectors.toList());

        // 并行：多线程执行，只需要加上 parallel 即可
        BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue<>(10000);
        // longList.stream().forEach(i -> { // 串行有序
        longList.stream().parallel().forEach(i -> { // 并行无序
            try {
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("blockingQueue" + blockingQueue.toString());
    }
}
