package javacore.lecture4.demo1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * @author Alan Yin
 * @date 2020/4/14
 */

/**
 * 1.软引用可以和一个引用队列联合使用，如果软件用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 * <p>
 * 2.当内存不足时，软引用对象被回收时，reference.get()为null，此时软引用对象的作用已经发挥完毕，这时将其添加进ReferenceQueue 队列中
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference reference = new SoftReference(obj, queue);
        // 亲自将引用对象置 null,保留软引用
        obj = null;
    }

    /**
     * 判断哪些软引用对象已经被清理
     */
    void clearSoftReferenceObjects() {
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference ref = null;
        while ((ref = (SoftReference) queue.poll()) != null) {
            // 清除软引用对象
        }
    }

}
