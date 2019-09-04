package effectivejava.chapter4.item34;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * 正例: Enum type with constant-specific method implementations
 * 特定于常量的方法实现可以与特定于常量的数据结合使用。
 * <p>
 * 例如，以下是 Operation 的一个版本，它重写 toString 方法以返回通常与该操作关联的符号：
 *
 * @author yinxing
 * @date 2019/9/4
 */

public enum OperationEnum02 {

    /**
     * arithmetic operation
     */
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    OperationEnum02(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = 2.0;
        double y = 4.0;
        for (OperationEnum02 op : OperationEnum02.values()) {
            System.out.printf("%f %s %f= %f %n", x, op, y, op.apply(x, y));
        }
    }


    /**
     * Implementing a fromString method on an enum type
     * 如果在枚举类型中重写 toString 方法，请考虑编写 fromString 方法将自定义字符串表示法转换回相应的枚举类型。
     * <p>
     * 下面的代码（类型名称被适当地改变）将对任何枚举都有效，只要每个常量具有唯一的字符串表示形式：
     * 方式1:在 values() 方法返回的数组上使用流
     */
    private static final Map<String, OperationEnum02> stringToEnum = Stream.of(values()).collect(
            toMap(Object::toString, e -> e));

    /**
     * 方式2:
     */
    private static final Map<String, OperationEnum02> stringToEnum2 = new HashMap<>();

    static {
        for (OperationEnum02 op : OperationEnum02.values()) {
            stringToEnum2.put(op.getSymbol(), op);
        }
    }

    /**
     * Returns Operation for string, if any
     */
    public static Optional<OperationEnum02> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

}
