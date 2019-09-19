package effectivejava.chapter8.item69;

/**
 * @author yinxing
 * @date 2019/9/19
 */

public class ExceptionDemo {

    /**
     * 反例:糟糕的滥用异常
     * Horrible abuse of exceptions. Don't ever do this!
     * <p>
     * 这种想法有三个错误：
     * 因为异常设计的初衷适用于不正常的情形，几乎没有 JVM 试图对他们进行优化，使它们与显式的测试一样快。
     * 把代码放在 try-catch 块中反而阻止了现代 JVM 实现本可能执行的某些特定优化。
     * 对数据进行遍历的标准模式并不会导致冗余的检查。有些 JVM 实现会将它们优化掉。
     */
    public void abuseException() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        try {
            int i = 0;
            while (true) {
                int value = array[i++];
                System.out.println(value);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    /**
     * 上面的例子这样处理即可：
     * 基于异常的循环模式不仅模糊了代码的意图，降低了它的性能，而且它还不能保证正常工作！
     */
    public void test() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : array) {
            System.out.println(array[i]);
        }
    }
}
