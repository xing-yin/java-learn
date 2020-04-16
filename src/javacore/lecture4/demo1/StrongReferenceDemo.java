package javacore.lecture4.demo1;

/**
 * 强引用
 *
 * @author Alan Yin
 * @date 2020/4/14
 */

public class StrongReferenceDemo {

    public static void main(String[] args) {
        // 强引用
        /**
         * 1.不可回收的资源（内存不足，JVM 抛出 OutOfMemoryError 异常终止，也不会回收）
         */
        Object o = new Object();
        /**
         *
         * 2.中断或者回收强引用对象,显示赋值为 null, jvm 会在合适时机回收
         */
        o = null;
    }
}
