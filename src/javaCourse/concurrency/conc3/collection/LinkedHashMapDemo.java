package javaCourse.concurrency.conc3.collection;

import java.util.*;

/**
 * @author Alan Yin
 * @date 2021/12/7
 */

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        // 1.test hash map
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("k3", "v3");
        hashMap.put("k2", "v2");
        hashMap.put("k1", "v1");
        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }

        // 2.test linked hash map
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("k3", "v3");
        linkedHashMap.put("k2", "v2");
        linkedHashMap.put("k1", "v1");
        Set<Map.Entry<String, String>> set2 = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, String> entry = iterator2.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }

    }
}
