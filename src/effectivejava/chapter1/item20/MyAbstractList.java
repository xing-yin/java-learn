package effectivejava.chapter1.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/8/26
 */

public class MyAbstractList {

    /**
     * 在框架实现之上构建的具体实现
     * Concrete implementation built atop skeletal implementation
     */
    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);
        // The diamond operator is only legal here in Java 9 and later
        // If you're using an earlier release, specify <Integer>
        // 匿名内部类
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                // Autoboxing
                return a[index];
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                // Auto-unboxing
                a[index] = element;
                // Auto-boxing
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
