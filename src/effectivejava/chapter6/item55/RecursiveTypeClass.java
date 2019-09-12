package effectivejava.chapter6.item55;

import java.util.*;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class RecursiveTypeClass {

    /**
     * Returns max value in a collection - throws exception if empty
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

    /**
     * Returns maximum value in collection as an Optional<E>
     * 上面方法更好的替代方式，不是抛出异常，而是使用 Optional
     */
    public static <E extends Comparable<E>> Optional<E> max2(Collection<E> c) {
        if (c.isEmpty()) {
            return Optional.empty();
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }

    /**
     * Returns max val in collection as Optional<E> - uses stream
     */
    public static <E extends Comparable<E>> Optional<E> max3(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder());
    }

    public static void main(String[] args) {
        Collection<String> wordList = Arrays.asList("a","b","c");
        // Using an optional to provide a chosen default value
        // 如果方法返回一个 Optional，则客户端可以选择在方法无法返回值时要采取的操作。 可以指定默认值
        String lastWordInLexicon =max2(wordList).orElse("No words");
        // 或者可以抛出任何适当的异常。注意，我们传递的是异常工厂，而不是实际的异常。这避免了创建异常的开销，除非它真的实际被抛出:
        String word2 = max2(wordList).orElseThrow(IllegalAccessError::new);
    }

}
