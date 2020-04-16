package javacore.lecture4.demo3;

import java.lang.ref.SoftReference;

/**
 * 软引用生命周期:
 * <p>
 * 只要执行GC，WeakReference 引用的对象就会被回收。
 * WeakReference 引用的对象的存活的生命周期是，下一次GC之前。
 *
 * @author Alan Yin
 * @date 2020/4/14
 */

public class WeakReferenceLifeCycle {

    public static void main(String[] args) {
        soft();
    }

    /**
     * 虚拟机参数:-Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
     */
    static void soft() {
        SoftReference[] softArr = new SoftReference[5];
        softArr[0] = new SoftReference<byte[]>(new byte[1024 * 1024 * 2]);
        System.out.println("GC 前===>" + softArr[0].get());
        System.err.println("================================ ");
        System.gc();
        System.out.println("第1次GC后：===>" + softArr[0].get());
        System.err.println("================================ ");

        softArr[1] = new SoftReference<byte[]>(new byte[1024 * 1024 * 2]);
        System.gc();
        System.out.println("第2次GC后：===>" + softArr[0].get());
        System.err.println("================================ ");

        softArr[2] = new SoftReference<byte[]>(new byte[1024 * 1024 * 2]);
        System.gc();
        System.out.println("第3次GC后：===>" + softArr[0].get());
        System.err.println("================================ ");

        softArr[3] = new SoftReference<byte[]>(new byte[1024 * 1024 * 2]);
        System.gc();
        // 最后获取 softArr[0] 的时候结果为 null，说明 SoftReference 引用被回收了。
        System.out.println("第4次GC后：===>" + softArr[0].get());
        System.err.println("================================ ");
    }


}
