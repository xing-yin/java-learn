package topic.stream_lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java 8 lambda表达式示例
 * 参考 https://juejin.im/post/6844903585558315016#heading-8
 *
 * @author Alan Yin
 * @date 2020/11/19
 */

public class StreamDemo {

    /**
     * 例1、用lambda表达式实现Runnable
     */
    private static void runnableTest() {
        // java 8 之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        // java 8
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !")).start();
    }

    /**
     * 例2、使用Java 8 lambda表达式进行事件处理
     */
    private static void eventProcessTest() {
        // java 8 之前
        JButton show = new JButton("show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        // java 8
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    // 简化 Collections.sort() 定制 Comparator
    private static void collectionSortCompareTest() {
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        // java 8 之前
        Collections.sort(features, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // java 8
        Collections.sort(features, (o1, o2) -> {
            return o1.compareTo(o2);
        });
    }

    /**
     * 例3、使用 lambda 表达式对列表进行迭代
     */
    private static void foreachTest() {
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        // java 8 之前
        for (String feature : features) {
            System.out.println(feature);
        }

        // java 8
        features.forEach(item -> System.out.println(item));
        // features.forEach(System.out::println); // 在Java 8中使用方法引用（method reference）
    }

    /**
     * 例4、使用lambda表达式和函数式接口Predicate
     * <p>
     * 使用 java.util.function.Predicate 函数式接口以及lambda表达式，可以向 API 方法添加逻辑，用更少的代码支持更多的动态行为。
     * <p>
     * 下面是Java 8 Predicate 的例子，展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
     */
    private static void predicateTest() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> ((String) str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> ((String) str).length() > 4);
    }

    private static void filter(List<String> names, Predicate condition) {
        // 方式1
//        for (String name : names) {
//            if (condition.test(name)) {
//                System.out.println(name + " ");
//            }
//        }

        // 方式2
        names.stream()
                .filter((name) -> (condition.test(name)))
                .forEach((name) -> System.out.println(name + " "));
    }

    /**
     * 例5、如何在lambda表达式中加入Predicate
     * <p>
     * java.util.function.Predicate 允许将两个或更多的 Predicate 合成一个。
     * 它提供类似于逻辑操作符AND和OR的方法，名字叫做 and()、or()
     */
    private static void lambdaPredicateTest() {
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        languages.stream()
                .filter(startWithJ.and(fourLetterLong));
    }

    /**
     * 例6、Java 8中使用 lambda 表达式的 Map 和 Reduce 示例
     */
    private static void mapTest() {
        // 不使用 Lambda 表达式为每个订单加上 12% 的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12 * cost;
            System.out.println(price);
        }

        // 使用 Lambda 表达式
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax2.stream()
                .map((cost) -> cost + cost * 0.12)
                .forEach(System.out::println);
    }

    // Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。
    // 另外，reduce 并不是一个新的操作，你有可能已经在使用它。
    // SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接收多个值并返回一个值。
    private static void reduceTest() {
        // 不使用 Lambda 表达式为每个订单加上 12% 的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12 * cost;
            total = total + price;
        }
        System.out.println(total);

        // 使用 Lambda 表达式
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        double total2 = 0;
        total2 = costBeforeTax2.stream()
                .map((cost) -> cost + cost * 0.12)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println(total2);
    }

    /**
     * 例7、通过过滤创建一个String列表
     * <p>
     * 流提供了一个 filter() 方法，接受一个 Predicate 对象，即可以传入一个 lambda 表达式作为过滤逻辑。
     * <p>
     * filter() 方法有个常见误解。平时做过滤时，通常会丢弃部分，但使用filter()方法则是获得一个新的列表，且其每个元素符合过滤原则。
     */
    private static void filterTest() {
        List<String> stringList = Arrays.asList("abc", "bcd", "defg", "jk");
        List<String> filteredStrs = stringList.stream()
                .filter(x -> x.length() > 2)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s \n filtered list : %s %n", stringList, filteredStrs);
    }

    /**
     * 例8、对列表的每个元素应用函数
     * <p>
     * 这个操作适合用 map() 方法，可以将转换逻辑以lambda表达式的形式放在 map() 方法里，就可以对集合的各个元素进行转换
     */
    private static void functionTest() {
        // 将字符串换成大写并用逗号链接起来
        List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String rs = g7.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(rs);
    }

    /**
     * 例9、复制不同的值，创建一个子列表
     */
    private static void distinctTest() {
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream()
                .distinct()
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 例10、计算集合元素的最大值、最小值、总和以及平均值
     * <p>
     * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。
     * 可以返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistics，描述流中元素的各种摘要数据。
     * <p>
     * 下面我们用这个方法来计算列表的最大值和最小值。它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总和及平均值。
     */
    private static void mathTest() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream()
                .mapToInt(x -> x)
                .summaryStatistics();
        System.out.println("max number: " + stats.getMax());
        System.out.println("min number: " + stats.getMin());
        System.out.println("sum number: " + stats.getSum());
        System.out.println("avg number: " + stats.getAverage());
    }

    public static void main(String[] args) {
//        runnableTest();

//        eventProcessTest();

//        foreachTest();

//        predicateTest();

//        mapTest();

//        reduceTest();

//        filterTest();

//        functionTest();

//        distinctTest();

        mathTest();
    }

    /**
     * Lambda表达式 vs 匿名类
     *
     * - 一个关键的不同点就是关键字 this。匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类。
     * - 另一个不同点是二者的编译方式。Java编译器将lambda表达式编译成类的私有方法。
     *      使用了Java 7的 invokedynamic 字节码指令来动态绑定这个方法。
     */

}

