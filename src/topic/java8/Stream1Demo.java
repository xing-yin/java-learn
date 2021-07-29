package topic.java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream 演示
 *
 * @author Alan Yin
 * @date 2021/7/29
 */

public class Stream1Demo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 2, 3, 5, 1, 2, 2, 7, 6);
        print(list);

        // Optional 可以避免空指针异常
        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first.map(i -> i * 10).orElse(66));

        int sum = list.stream()
                .filter(i -> i < 4)
                .distinct()
                // reduce 做规约，需要给定一个初始值(如这里的 0)
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum=" + sum);

        int multi = list.stream()
                .filter(i -> i < 4)
                .distinct()
                .reduce(1, (a, b) -> a * b);
        System.out.println("multi=" + multi);

        Map<Integer, Integer> map = list.stream().parallel()
                // 当 key 重复时，需要这样处理：(a, b) -> a 表示 key 重复时保留第一个
                .collect(Collectors.toMap(a -> a, a -> a + 1, (a, b) -> a, LinkedHashMap::new));
        print(map);

        map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
        List<Integer> mapToList = map.entrySet().stream().parallel()
                .map(e -> e.getKey() + e.getValue()).collect(Collectors.toList());
        print(mapToList);

        // 小结：
        // 1.Fluent API:继续 stream
        // 2.终止操作：得到最终结果
    }

    private static void print(Object object) {
        System.out.println(object);
    }
}
