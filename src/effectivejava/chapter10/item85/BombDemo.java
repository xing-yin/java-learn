package effectivejava.chapter10.item85;

import java.util.HashSet;
import java.util.Set;

/**
 * 反例: 一个例子，它只使用哈希集和字符串
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class BombDemo {

    /**
     * 对象图由 201 个 HashSet 实例组成，每个实例包含 3 个或更少的对象引用。
     * 整个流的长度为 5744 字节，但是在你对其进行反序列化之前，资源就已经耗尽了。
     * <p>
     * 问题在于，反序列化 HashSet 实例需要计算其元素的哈希码。
     * 根哈希集的 2 个元素本身就是包含 2 个哈希集元素的哈希集，每个哈希集元素包含 2 个哈希集元素，以此类推，深度为 100。
     * 因此，反序列化 Set 会导致 hashCode 方法被调用超过 2100 次。
     * 除了反序列化会持续很长时间之外，反序列化器没有任何错误的迹象。生成的对象很少，并且堆栈深度是有界的。
     *
     * @return
     */
    static byte[] bomb() {
        Set<Object> root = new HashSet<>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();
            // make t1 unequal to t2
            t1.add("foo");
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
        // method omitted for brevity
        // 为了简洁而省略的方法
        return serialize(root);
    }

    private static byte[] serialize(Set<Object> root) {
        return new byte[6];
    }

}
