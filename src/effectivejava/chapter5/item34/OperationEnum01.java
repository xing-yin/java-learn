package effectivejava.chapter5.item34;

/**
 * 正例: Enum type with constant-specific method implementations
 * 特定于常量（constant-specific）的方法实现
 *
 * @author yinxing
 * @date 2019/9/4
 */

public enum OperationEnum01 {

    /**
     * arithmetic operation
     */
    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    /**
     * 更好的方法可以将不同的行为与每个枚举常量关联起来：
     * 在枚举类型中声明一个抽象的 apply 方法，并用常量特定的类主体中的每个常量的具体方法重写它
     */
    public abstract double apply(double x, double y);

}
