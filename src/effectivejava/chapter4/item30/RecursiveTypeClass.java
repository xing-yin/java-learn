package effectivejava.chapter4.item30;

import java.util.Collection;
import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class RecursiveTypeClass {

    /**
     * Returns max value in a collection - uses recursive type bound
     * 使用递归类型绑定
     */
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Empty collection");
        }

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

}
