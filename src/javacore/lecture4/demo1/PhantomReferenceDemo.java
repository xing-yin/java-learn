package javacore.lecture4.demo1;

/**
 * @author Alan Yin
 * @date 2020/4/14
 */

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 1.不影响对象的生命周期。如果一个对象与虚引用关联，则跟没有引用与之关联一样，随时都可能被 GC 回收。
 * <p>
 * 2.注意：虚引用必须和引用队列关联使用，当 GC 准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加到与之关联的引用队列中。
 * <p>
 * 3.可通过判断引用队列中是否已加入虚引用，来了解被引用的对象是否将要被垃圾回收。
 * 如果某个虚引用已被加入到引用队列，就可以在所引用的对象的内存被回收之前采取必要的行动。
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference reference = new PhantomReference(obj, queue);
        // 强引用对象滞空，保留虚引用
        obj = null;
    }
}
