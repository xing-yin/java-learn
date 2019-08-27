package effectivejava.chapter4.item27;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 如果你不能消除警告，但你可以证明引发警告的代码是类型安全的，那么（并且只能这样）用 @SuppressWarnings(“unchecked”) 注解来抑制警告。
 *
 * @author yinxing
 * @date 2019/8/27
 */

public class UncheckedClass<T> {

    // unchecked conversion:没有 <>
    // Set<String> set1 = new HashSet();
    Set<String> set2 = new HashSet<>();

    private int size;

    private Object[] elements;

    /**
     * 反例:如果编译 ArrayList 类，则该方法会生成此警告：[unchecked] unchecked cast
     * 因为尝试将 Object 转为 范性 T
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // [unchecked] unchecked cast
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * 正例:Adding local variable to reduce scope of @SuppressWarnings
     * 声明一个局部变量来保存返回值并标注它的声明
     */
    public <T> T[] toArray2(T[] a) {
        if (a.length < size) {
            // This cast is correct because the array we're creating
            // is of the same type as the one passed in, which is T[].
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

}
