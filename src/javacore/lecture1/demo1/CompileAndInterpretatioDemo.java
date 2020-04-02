package javacore.lecture1.demo1;

/**
 * 编译执行与解释执行: Java虚拟机启动时，可以指定不同的参数对运行模式进行选择
 *
 * @author Alan Yin
 * @date 2020/4/1
 */

public class CompileAndInterpretatioDemo {

    /**
     * 【只解释执行】-Xint
     *
     * 最慢：指定“-Xint”，就是告诉JVM只进行解释执行，不对代码进行编译，这种模式抛弃了JIT可能带来的性能优势
     */

    /**
     * 【只编译执行】-Xcomp
     *
     * 有一个“-Xcomp”参数，这是告诉JVM关闭解释器，不要进行解释执行，或者叫作最大优化级别
     * <p></p>
     * 这种模式是不是最高效啊?简单说，还真未必。
     * “-Xcomp”会导致JVM启动变慢非常多，同时有些JIT编译器优化方式，比如分支预测，如果不进行profling，往往并不能进 行有效优化。
     */

    /**
     *【混合模式: 解释执行 + 编译执行】-Xmixed
     *
     * 如主流 java 版本:jdk1.8
     */

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < 1000_000_000; i++) {
            i = i + 1;
        }
        System.out.println("use time:" + (System.currentTimeMillis() - startTime));
    }

    // -Xint  use time:9350
    // -Xcomp use time:185
    // 混合模式 use time:279
}
