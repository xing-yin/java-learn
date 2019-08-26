package effectivejava.chapter1.item19;

import java.time.Instant;

/**
 * @author yinxing
 * @date 2019/8/23
 */

public class Sub extends Super {

    /**
     * Blank final, set by constructor
     */
    private final Instant instant;

    Sub() {
        this.instant = Instant.now();
    }

    /**
     * Overriding method invoked by superclass constructor
     * 重写的方法被超类调用
     */
    @Override
    public void overrideMethod() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        /**
         * 结果将打印两次: 一次 null; 一次当前时间
         *
         * 你可能期望这个程序打印两次 instant 实例，但是它第一次打印出 null，
         * 因为在 Sub 构造方法有机会初始化 instant 属性之前，overrideMe 被 Super 构造方法调用。
         *
         * 请注意，这个程序观察两个不同状态的 final 属性！
         *
         * 还要注意的是，如果 overrideMe 方法调用了 instant 实例中任何方法，那么当父类构造方法调用 overrideMe 时，它将抛出一个 NullPointerException 异常。
         * 这个程序不会抛出 NullPointerException 的唯一原因是 println 方法容忍 null 参数。
         */
        Sub sub = new Sub();
        sub.overrideMethod();
    }
}
