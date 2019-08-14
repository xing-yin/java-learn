package effectivejava.chapter1.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 容易发生内存泄漏情形1: 当一个类自己管理内存时，程序员应该警惕内存泄漏问题
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class Stack {

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    /**
     * 反例: 内存泄漏
     * <p>
     * 如果一个栈增长后收缩，那么从栈弹出的对象不会被垃圾收集，即使使用栈的程序不再引用这些对象。
     * 这是因为栈维护对这些对象的过期引用（obsolete references）。
     * 在这种情况下，元素数组“活动部分（active portion）”之外的任何引用都是过期的。
     * 活动部分是由索引下标小于 size 的元素组成。
     *
     * @return
     */
    public Object pop1() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // --size 是因为数组的索引是从0开始的
        return elements[--size];
    }

    /**
     * 正例：一旦对象引用过期，将它们设置为 null。
     *
     * @return
     */
    public Object pop2() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        // 将过期的对象设为 null,这样 JVM 经过可达性分析就可以将该对象垃圾回收
        elements[size] = null;
        return result;
    }
}
