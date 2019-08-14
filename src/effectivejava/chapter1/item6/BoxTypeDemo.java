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

    // 反例:使用包装类型(实际没有必要)  9302ms
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

    // 正例：使用基本数据库类型     759ms
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
