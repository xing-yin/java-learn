package javacore.lecture4.demo2;

/**
 * @author Alan Yin
 * @date 2020/4/14
 */

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 1. WeakHashMap 存放了键对象的弱引用,当键对象被垃圾回收器回收时,相应的值对象的引用会从 Map 中删除.
 * <p>
 * 2. WeakHashMap 能够节约储存空间,可用来缓存那些非必须存在的数据.
 * <p>
 * 3. WeakHashMap 通过 expungeStaleEntries() 实现的,WeakHashMap 内置了一个ReferenceQueue,来获取键对象的引用情况.
 * (这个方法相当于遍历 ReferenceQueue,然后将已经被回收的键对象,对应的值对象置 null)
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        // testOOM();
        testRelease();
    }

    /**
     * expungeStaleEntries() 不是自动调用的,需要外部对 WeakHashMap 对象查询或操作,才会触发自动释放
     * <p>
     * Java 默认内存是 64M，所以不改变内存参数时，测试跑不了几步循环就内存溢出了。结果可见，WeakHashMap 这个时候并没有自动帮我们释放不用的内存。
     */
    static void testOOM() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 100; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<>();
            d.put(new byte[10000][10000], new byte[10000][10000]);
            maps.add(d);
            System.gc();
            System.out.println(i);
        }
    }

    /**
     * expungeStaleEntries() 不是自动调用的,需要外部对 WeakHashMap 对象查询或操作,才会触发自动释放
     * <p>
     * 因此，WeakHashMap 并不是自动进行键值的垃圾回收操作的,而需要做对WeakHashMap的访问操作这时候才进行对键对象的垃圾回收清理.
     */
    static void testRelease() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<>();
            d.put(new byte[10000][10000], new byte[10000][10000]);
            maps.add(d);
            // 不一定会执行
            System.gc();
            System.out.println(i);

            for (int j = 0; j < i; j++) {
                System.err.println(j + "size" + maps.get(j).size());
            }
        }
    }

}
