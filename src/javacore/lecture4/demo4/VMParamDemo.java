package javacore.lecture4.demo4;

/**
 * @author Alan Yin
 * @date 2020/4/14
 */

public class VMParamDemo {

    /**
     * 1.XX:+PrintGCDetails 打印GC中的变化
     * <p>
     * 参考:https://www.jianshu.com/p/fd1d4f21733a
     */
    static void test1() {
        // -Xms10M -XX:+PrintGCDetails
    }

    /**
     * 2.设置堆最大为20M，最小为 10M, 新生代大小为5M
     * -Xmx20M 最大为20M
     * -Xms10M
     * -Xmn5M
     */

    /**
     * 3.verbose:gc 显示GC的操作内容
     * <p>
     * 4.XX:+UseSerialGC
     * 新生代和老年代中使用串行收集器，由于-verbose:gc参数对Parallel Scavenge收集器不起作用，无法显示显示GC的操作内容，因此采用串行收集器
     */

    public static void main(String[] args) {
        test1();
    }
}
