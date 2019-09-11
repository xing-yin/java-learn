package effectivejava.chapter6.item53;

/**
 * 性能关键的情况下使用可变参数时要小心。每次调用可变参数方法都会导致数组分配和初始化。
 * 如果你从经验上确定负担不起这个成本，但是还需要可变参数的灵活性，那么有一种模式可以让你鱼与熊掌兼得。
 * 假设你已确定 95％ 的调用是三个或更少的参数的方法，那么声明该方法的五个重载。EnumSet 中的 of 方法，如下所示，
 *
 * @author yinxing
 * @date 2019/9/11
 */

public class Example {

    public void foo() {
    }

    public void foo(int a1) {
    }

    public void foo(int a1, int a2) {
    }

    public void foo(int a1, int a2, int a3) {
    }

    public void foo(int a1, int a2, int a3, int... rest) {
    }

    /**
     * 总之，当需要使用可变数量的参数定义方法时，可变参数非常有用。
     * 在使用可变参数前加上任何必需的参数，并注意使用可变参数的性能后果。
     */
}
