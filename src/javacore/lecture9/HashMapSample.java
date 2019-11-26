package javacore.lecture9;

import java.util.HashMap;
import java.util.Map;

/**
 * 第9讲 | 对比Hashtable、HashMap、TreeMap有什么不同?
 *
 * @author yinxing
 * @date 2019/11/26
 */

public class HashMapSample {

    public static void main(String[] args) {
        //hashMap();
    }

    /**
     * hashMap 的一些操作
     */
    private static void hashMap() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("k1", "v1");
        // k-v 都是 null 不会被保存
        map.put(null, null);
        map.put(null, "v3");
        map.put("k4", null);
        for (Map.Entry<String, Object> item : map.entrySet()) {
            System.out.print(item.getKey());
            System.out.print(":");
            System.out.print(item.getValue());
            System.out.println();
        }
        map.remove("k1");
        map.get("k4");
    }
}
