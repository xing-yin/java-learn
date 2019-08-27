package effectivejava.chapter4.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 容易发生内存泄漏情形1: 当一个类自己管理内存时，程序员应该警惕内存泄漏问题
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class Stack<E> {

    private E[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     *
     * The elements array will contain only E instances from push(E).
     * This is sufficient to ensure type safety, but the runtime
     * type of the array won't be E[]; it will always be Object[]!
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        // 将过期的对象设为 null,这样 JVM 经过可达性分析就可以将该对象垃圾回收
        elements[size] = null;
        return result;
    }
}
