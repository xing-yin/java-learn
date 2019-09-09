package effectivejava.chapter5.item42;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yinxing
 * @date 2019/9/9
 */

public class Function {

    /**
     * 反例:Anonymous class instance as a function object - obsolete!
     * 匿名内部类实例化一个函数对象-过时的
     * <p>
     * 匿名类适用于需要函数对象的经典面向对象设计模式，特别是策略模式[Gamma95]。
     * 比较器接口表示排序的抽象策略; 上面的匿名类是排序字符串的具体策略。
     * 然而，匿名类的冗长，使得 Java 中的函数式编程成为一种吸引人的新选择。
     */
    private class AnonymousClass {
        private void test() {
            List<String> wordList = Arrays.asList("aaa", "bbb", "cccc");
            Collections.sort(wordList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return Integer.compare(s1.length(), s2.length());
                }
            });
        }
    }

    /**
     * 正例：lambdas 替换上面的匿名类
     * 在 Java 8 中，语言形式化了函数式编程的概念，即使用单个抽象方法的接口是特别的，应该得到特别的对待。
     * 这些接口现在称为函数式接口，并且该语言允许你使用 lambda 表达式或简称 lambda 来创建这些接口的实例。
     * Lambdas 在功能上与匿名类相似，但更为简洁。
     */
    private void test2() {
        List<String> wordList = Arrays.asList("aaa", "bbb", "cccc");
        // 方式1:
        // Collections.sort(wordList, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        // 方式2:如果使用比较器构造方法代替 lambda，则代码中的比较器可以变得更加简洁
        Collections.sort(wordList, Comparator.comparingInt(String::length));
        // 方式3:通过利用添加到 Java 8 中的 List 接口的 sort 方法，可以使片段变得更简短：
        wordList.sort(Comparator.comparingInt(String::length));
    }

}
