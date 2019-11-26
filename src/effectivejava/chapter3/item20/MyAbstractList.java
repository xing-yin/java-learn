package effectivejava.chapter3.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * 当你考虑一个 List 实现为你做的所有事情时，这个例子是一个骨架实现的强大的演示。
 * <p>
 * 按照惯例，骨架实现类被称为 AbstractInterface，其中 Interface 是它们实现的接口的名称。
 * <p>
 * 例如，集合框架（ Collections Framework）提供了一个框架实现以配合每个主要集合接口：AbstractCollection，AbstractSet，AbstractList 和 AbstractMap。
 *
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
