package javacore.lecture4.demo3;

import java.lang.ref.WeakReference;

/**
 * 软引用生命周期：
 * <p>
 * - 只有在内存空间不足时（将要内存溢出），SoftReference 引用的对象才会被回收。
 * - 内存空间充足的情况下，不管执行多少次GC都不会被回收。
 *
 * @author Alan Yin
 * @date 2020/4/14
 */

public class SoftReferenceLifeCycle {

    public static void main(String[] args) {
        weak();
    }

    /**
     * 虚拟机参数:-Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
     */
    static void weak() {
        WeakReference<Integer> weakReference = new WeakReference<Integer>(new Integer(100));
        System.out.println("GC 前====>" + weakReference.get());
        System.gc();
        System.out.println("GC 后====>" + weakReference.get());
    }


}
