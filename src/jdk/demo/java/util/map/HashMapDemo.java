package jdk.demo.java.util.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 演示 hashmap
 *
 * @author Alan Yin
 * @date 2021/10/9
 */

public class HashMapDemo {

    public static void main(String[] args) {
//        methodTest();

        deadLockTest();

//        iteratorTest();
    }

    /**
     * 遍历方式测试
     */
    private static void iteratorTest() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "null value");
        map.put("k1", "v1");
        map.put("k2", null);

        // 遍历方式1
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }

        // 遍历方式2
        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }

        // 遍历方式3【不推荐】
        // 无论是 1.7 还是 1.8, JDK 没有对它做任何的同步操作，所以并发会出问题，
        //甚至 1.7 中出现死循环导致系统不可用（1.8 已经修复死循环问题）。
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println("key=" + key + ",value=" + map.get(key));
        }
    }

    /**
     * 基础方法测试
     */
    private static void methodTest() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "null value");
        map.put("k1", "v1");
        map.put("k2", null);
        System.out.println(map.toString());

        System.out.println("-------------");
        System.out.println(map.get(null));
        System.out.println(map.get("k1"));
        System.out.println(map.get("k2"));
    }

    /**
     * HashMap 死锁测试
     */
    private static void deadLockTest() {
        final HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString(), "");
            }).start();
        }
        System.out.println("finish");
    }
}
