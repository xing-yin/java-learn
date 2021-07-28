package topic.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合常见操作演示
 *
 * @author Alan Yin
 * @date 2021/7/28
 */

public class CollectionDemo {

    public static void main(String[] args) {
        // Arrays 还可以包装 stream, 如 Arrays.asList(list.stream());
        List<Integer> list = Arrays.asList(4, 2, 3, 5, 1, 2, 2, 7, 6);
        print(list);

        Collections.sort(list);
        print(list);

        Collections.reverse(list);
        print(list);

        Collections.shuffle(list);
        print(list);

        System.out.println(Collections.frequency(list, 2));
        System.out.println(Collections.max(list));

        Collections.fill(list, 9);
        print(list);

        list = Collections.singletonList(6);
        print(list);
    }

    private static void print(List<Integer> list) {
        System.out.println(String.join(",",
                list.stream()
                        .map(i -> i.toString())
                        .collect(Collectors.toList())
                        .toArray(new String[]{})));
    }

}
