package effectivejava.chapter5.item44;

/**
 * 总之，现在 Java 已经有了 lambda 表达式，因此必须考虑 lambda 表达式来设计你的 API。 在输入上接受函数式接口类型并在输出中返回它们。
 * <p>
 * 一般来说，最好使用 java.util.function.Function 中提供的标准接口，但请注意，在相对罕见的情况下，最好编写自己的函数式接口。
 *
 * @author yinxing
 * @date 2019/9/9
 */
@FunctionalInterface
public interface Fuction {

    void test1();

    /**
     * 如果使用了 @FunctionalInterface 注解，可以防止在接口发生变化时不小心地将抽象方法添加到接口中
     */
    // abstract void test2();
}
