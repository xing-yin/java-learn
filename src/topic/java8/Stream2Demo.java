package topic.java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream 演示
 *
 * @author Alan Yin
 * @date 2021/7/29
 */

public class Stream2Demo {

    public static void main(String[] args) {
        System.out.println(" Java 7 演示：");
        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("原始列表为:" + strings);

        int count = getEmptyStringCountByJava7(strings);
        System.out.println("空字符串数量为：" + count);

        count = getLength3CountByJava7(strings);
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filteredList = deleteEmptyStringsByJava7(strings);
        System.out.println("删除空字符串后的列表: " + filteredList);

        String mergedString = getMergedStringByJava7(strings, ",");
        System.out.println("合并后的字符串: " + mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = getSquaresList(numbers);
        System.out.println("平方数列表: " + squaresList);

        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        System.out.println("列表: " + integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出 10 个随机数
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("随机数" + random.nextInt());
        }

        System.out.println(" Java 8 演示：");
        long count2 = (int) strings.stream().filter(e -> e.isEmpty()).count();
        System.out.println("空字符串数量为：" + count2);

        count2 = strings.stream().filter(e -> e.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count2);

        List<String> filteredList2 = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("删除空字符串后的列表: " + filteredList2);

        String mergedString2 = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并后的字符串: " + mergedString2);

        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList2 = numbers2.stream().map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println("平方数列表: " + squaresList2);

        List<Integer> integers2 = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        IntSummaryStatistics stat = integers2.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("列表: " + integers2);
        System.out.println("列表中最大的数 : " + stat.getMax());
        System.out.println("列表中最小的数 : " + stat.getMin());
        System.out.println("所有数之和 : " + stat.getSum());
        System.out.println("平均数 : " + stat.getAverage());
        System.out.println("随机数: ");
        random.ints().limit(10).forEach(System.out::println);

        count2 = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count2);
    }

    private static int getAverage(List<Integer> integers) {
        return getSum(integers) / integers.size();
    }

    private static int getSum(List<Integer> integers) {
        int sum = 0;
        for (Integer num : integers) {
            sum += num;
        }
        return sum;
    }

    private static int getMin(List<Integer> integers) {
        int min = integers.get(0);
        for (Integer num : integers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static int getMax(List<Integer> integers) {
        int max = integers.get(0);
        for (Integer num : integers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static List<Integer> getSquaresList(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<>(numbers.size());
        for (Integer num : numbers) {
            Integer square = num * num;
            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static String getMergedStringByJava7(List<String> strings, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s).append(delimiter);
        }
        String mergedString = sb.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<String> deleteEmptyStringsByJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<>();
        for (String s : strings) {
            if (!s.isEmpty()) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    private static int getLength3CountByJava7(List<String> strings) {
        int count = 0;
        for (String s : strings) {
            if (s.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static int getEmptyStringCountByJava7(List<String> strings) {
        int count = 0;
        for (String s : strings) {
            if (s.isEmpty()) {
                count++;
            }
        }
        return count;
    }

}
