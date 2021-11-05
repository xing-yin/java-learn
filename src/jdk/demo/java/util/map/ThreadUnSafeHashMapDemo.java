package jdk.demo.java.util.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示：HashMap 线程不安全
 *
 * @author Alan Yin
 * @date 2021/10/21
 */

public class ThreadUnSafeHashMapDemo {

    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        // Thread 1
        new Thread(() -> {
            for (int i = 0; i < 99999999; i++) {
                map.put("thread1_key" + i, "thread1_value" + i);
            }
        }).start();

        // Thread 2
        new Thread(() -> {
            for (int i = 0; i < 99999999; i++) {
                map.put("thread2_key" + i, "thread2_value" + i);
            }
        }).start();

        System.out.println(map.get("thread1_key" + 1000));
    }
}
