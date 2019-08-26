package effectivejava.chapter2.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author yinxing
 * @date 2019/8/20
 */

public class Stack implements Cloneable{

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        // 将过期的对象设为 null,这样 JVM 经过可达性分析就可以将该对象垃圾回收
        elements[size] = null;
        return result;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    /**
     * 反例: 仅返回 super.clone() 调用的对象，那么生成的 Stack 实例在其 size 属性中具有正确的值，但 elements 属性引用与原始 Stack 实例相同的数组。
     * 修改原始实例将破坏克隆中的不变量，反之亦然。即"浅拷贝"
     */
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    /**
     * 正例: 前提是 elements 属性不是 final 的。
     * 如果 elements 属性是 final 的，则下面的解决方案将不起作用，因为克隆将被禁止向该属性分配新的值。
     *
     * 备注:有的时候，递归地调用 clone 并不是足够的。参见 HashTable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
         Stack stack=(Stack) super.clone();
         // 要将内部的属性引用拷贝给 stack,这是深拷贝
         stack.elements  = elements.clone();
         return stack;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
//        // 反例验证: elements 属性引用与原始 Stack 实例相同的数组。修改原始实例将破坏克隆中的不变量，反之亦然。
//        Stack stack1 = new Stack();
//        stack1.push(1);
//        stack1.push(2);
//        stack1.push(3);
//        // Stack{elements=[1, 2, 3, null, null, null, null, null, null, null, null, null, null, null, null, null], size=3}
//        System.out.println(stack1);
//        Stack stack2 =(Stack) stack1.clone();
//        stack2.pop();
//        // Stack{elements=[1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null], size=3}
//        System.out.println(stack1);

        Stack stack2 = new Stack();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        // Stack{elements=[1, 2, 3, null, null, null, null, null, null, null, null, null, null, null, null, null], size=3}
        System.out.println(stack2);
        Stack stack3 =(Stack) stack2.clone();
        stack3.pop();
        // Stack{elements=[1, 2, 3, null, null, null, null, null, null, null, null, null, null, null, null, null], size=3}
        System.out.println(stack2);


    }
}
