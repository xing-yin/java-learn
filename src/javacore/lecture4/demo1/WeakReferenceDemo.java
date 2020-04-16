package javacore.lecture4.demo1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @author Alan Yin
 * @date 2020/4/14
 */

/**
 * 弱引用和软引用的区别：弱引用的对象生命周期更短，只要垃圾回收器扫描到它，不管内存空间充足与否，都会回收。
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue queue = new ReferenceQueue();
        WeakReference reference = new WeakReference(obj, queue);
        // 强引用对象置 null, 保留弱引用
        obj = null;
    }
}
