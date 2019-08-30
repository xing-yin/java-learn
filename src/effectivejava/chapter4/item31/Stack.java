package effectivejava.chapter4.item31;

import java.util.*;

/**
 * @author yinxing
 * @date 2019/8/28
 */

public class Stack<E> {

    private E[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private boolean empty;

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

    private boolean isEmpty() {
        return elements.length == 0;
    }

    /**
     * 反例: pushAll method without wildcard type - deficient!
     * pushAll 方法没有类型通配符 - 有缺陷
     * <p>
     * 如果可遍历的 src 元素类型与栈的元素类型完全匹配，那么它工作正常。
     * 但是，假设有一个 Stack<Number>，并调用 push(intVal)，其中 intVal 的类型是 Integer。
     * 这是因为 Integer 是 Number 的子类型。 从逻辑上看，这似乎也应该起作用：
     */
    public void pushAll1(Iterable<E> src) {
        for (E e : src) {
            push(e);
        }
    }

    /**
     * 正例: Wildcard type for a parameter that serves as an E producer
     * 作为 E 生产者的参数通配符类型
     * <p>
     * pushAll 的输入参数的类型不应该是「E 的 Iterable 接口」，而应该是「E 的某个子类型的 Iterable 接口」，并且有一个通配符类型，
     * 这意味着：Iterable<? extends E>。
     */
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    /**
     * 反例: popAll method without wildcard type - deficient!
     */
    public void popAll1(Collection<E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    /**
     * 正例: Wildcard type for parameter that serves as an E consumer
     * 作为 E 消费者的参数通配符类型
     * <p>
     * popAll 的输入参数的类型不应该是「E 的集合」，而应该是「E 的某个父类型的集合」（其中父类型被定义为 E 是它自己的父类型[JLS，4.10]）。
     *
     * @param dst
     */
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public static void main(String[] args) {
        /**
         * 情形1： <? extends E>
         */
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integerIterable = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
        };
        // 报错: Iterable<Integer> cannot be converted to Iterable<Number>
        // numberStack.pushAll1(integerIterable);
        // ok
        numberStack.pushAll(integerIterable);

        /**
         * 情形2： <? super E>
         */
        Stack<Number> numberStack2 = new Stack<>();
        Collection<Object> objectCollection = new ArrayList<>(Arrays.asList("a", 1, 2));
        // 报错: java.util.Collection<java.lang.Object>无法转换为java.util.Collection<java.lang.Number>
        // numberStack2.popAll1(objectCollection);
        // ok
        numberStack2.popAll(objectCollection);
    }


}
