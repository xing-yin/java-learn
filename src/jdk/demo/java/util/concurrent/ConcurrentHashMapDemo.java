package jdk.demo.java.util.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 演示 ConcurrentHashMap
 *
 * @author Alan Yin
 * @date 2021/10/9
 */

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
        // java.lang.NullPointerException
        // ConcurrentHashMap 不同于 HashMap，key 和 value 均不允许为 null
        map.put(null,"null value");
        map.put("k2",null);
    }
}
