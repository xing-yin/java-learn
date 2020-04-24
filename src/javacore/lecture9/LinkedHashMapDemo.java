package javacore.lecture9;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap通常提供的是遍历顺序符合插入顺序，它的实现是通过为条目(键值对)维护一个双向链表。
 * 注意，通过特定构造函数，我们可以创建反映访问顺序的实例，所 谓的put、get、compute等，都算作“访问”。
 *
 * @author yinxing
 * @date 2019/11/26
 */

/**
 * 这种行为适用于一些特定应用场景：
 * 例如，我们构建一个空间占用敏感的资源池，希望可以自动将最不常被访问的对象释放掉，这就可以利用LinkedHashMap提供的机制来实现，
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        LinkedHashMap<String, String> accessOrderMap = new LinkedHashMap<String, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3;
            }
        };
        accessOrderMap.put("project1", "value1");
        accessOrderMap.put("project2", "value2");
        accessOrderMap.put("project3", "value3");
        accessOrderMap.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("init done===========");
        // 模拟访问(project3 先被访问，则先被移除)
        accessOrderMap.get("project3");
        accessOrderMap.get("project1");
        accessOrderMap.get("project2");
        accessOrderMap.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("visit done===========");
        // 触发删除
        accessOrderMap.put("project4", "value4");
        accessOrderMap.forEach((k, v) ->
                System.out.println(k + ":" + v));
        System.out.println("removeEldest done===========");
    }
}
