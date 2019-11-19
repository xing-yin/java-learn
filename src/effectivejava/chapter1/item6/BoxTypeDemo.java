package effectivejava.chapter1.item6;

import java.util.Date;

/**
 * 优先使用基本类型而不是装箱的基本类型，也要注意无意识的自动装箱
 * <p>
 * java 的自动装箱拆箱会消耗性能
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class BoxTypeDemo {

    /**
     * 反例:使用包装类型(实际没有必要)  9302ms
     */
    public static long sum1() {
        // 无意识的自动装箱
        Long sum = 0L;
        Date start = new Date();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum = sum + i;
        }
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        System.out.println("sum1 time is " + time);
        return sum;
    }

    /**
     * 正例：使用基本数据库类型     759ms
     */
    public static long sum2() {
        long sum = 0L;
        Date start = new Date();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum = sum + i;
        }
        Date end = new Date();
        long time = end.getTime() - start.getTime();
        System.out.println("sum2 time is " + time);
        return sum;
    }

    public static void main(String[] args) {
        sum1();
        sum2();
    }
}

/**
 * 结论：
 * <p>
 * 这个条目不应该被误解为暗示对象创建是昂贵的，应该避免创建对象。
 * 相反，使用构造方法创建和回收小的对象是非常廉价，构造方法只会做很少的显示工作，尤其是在现代 JVM 实现上。
 * 创建额外的对象以增强程序的清晰度，简单性或功能性通常是件好事。
 * <p>
 * 相反，除非池中的对象非常重量级，否则通过维护自己的对象池来避免对象创建是一个坏主意。
 * 对象池的典型例子就是数据库连接。建立连接的成本非常高，因此重用这些对象是有意义的。
 * 但是，一般来说，维护自己的对象池会使代码混乱，增加内存占用，并损害性能。
 * 现代 JVM 实现具有高度优化的垃圾收集器，它们在轻量级对象上轻松胜过此类对象池。
 * <p>
 * 这个条目的对应点是针对条目 50 的防御性复制（defensive copying）。
 * 目前的条目说：「当你应该重用一个现有的对象时，不要创建一个新的对象」，而条目 50 说：「不要重复使用现有的对象，当你应该创建一个新的对象时。」
 * 请注意，重用防御性复制所要求的对象所付出的代价，要远远大于不必要地创建重复的对象。
 * 未能在需要的情况下防御性复制会导致潜在的错误和安全漏洞；而不必要地创建对象只会影响程序的风格和性能。
 */
