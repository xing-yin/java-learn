package effectivejava.chapter5.item42;

import java.util.function.DoubleBinaryOperator;

/**
 * Lambdas 可以很容易地使用前者而不是后者来实现常量特定的行为。
 * 仅仅将实现每个枚举常量行为的 lambda 传递给它的构造方法。
 * 构造方法将 lambda 存储在实例属性中，apply 方法将调用转发给 lambda。
 * <p>
 * 由此产生的代码比原始版本更简单，更清晰，见下面的示例:
 *
 * @author yinxing
 * @date 2019/9/9
 */

public enum OperationEnum2 {

    /**
     * Operation
     */
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    /**
     * 我们使用表示枚举常量行为的 lambdas 的 DoubleBinaryOperator 接口。 这是 java.util.function 中许多预定义的函数接口之一（详见第 44 条）。
     * 它表示一个函数，它接受两个 double 类型参数并返回 double 类型的结果。
     */
    private final DoubleBinaryOperator op;

    OperationEnum2(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}
