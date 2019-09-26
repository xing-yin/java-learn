package effectivejava.chapter9.item78;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 慎重使用 volatile
 *
 * @author yinxing
 * @date 2019/9/26
 */

public class VolatileDemo {

    private static volatile int nextSerialNumber = 0;

    /**
     * 反例: 没有慎重使用 volatile,没有同步
     * <p>
     * 如果没有同步，这个方法无法正确地工作:
     * <p/>
     * 问题在于，增量操作符（++）不是原子的。它在 nextSerialNumber 字段中执行两项操作：首先它读取值，然后写回一个新值，相当于原来的值再加上 1。
     * 如果第二个线程在第一个线程读取旧值和写回新值期间读取这个字段.第二个线程就会与第一个线程一起看到同一个值，并返回相同的序列号。
     * 这就是安全性失败（ safety failure ）：这个程序会计算出错误的结果。
     */
    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }

    /**
     * 正例:使用 synchronized [不是最好方式]
     */
    private static long nextSerialNumber2 = 0;

    public static synchronized long generateSerialNumber2() {
        return nextSerialNumber2++;
    }

    /**
     * 正例:使用 AtomicLong [推荐 java.util.concurrent.atomic 包类]
     */
    private static final AtomicLong nextSerialNumber3 = new AtomicLong();

    public static synchronized long generateSerialNumber3() {
        return nextSerialNumber3.incrementAndGet();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            generateSerialNumber3();
        }
        System.out.println(generateSerialNumber3());
    }
}
