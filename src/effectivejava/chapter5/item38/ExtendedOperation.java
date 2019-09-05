package effectivejava.chapter5.item38;


import java.util.Arrays;
import java.util.Collection;

/**
 * @author yinxing
 * @date 2019/9/5
 */

public enum ExtendedOperation implements Operation {

    /**
     * arithmetic operation
     */
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    /**
     * opEnumType 参数中复杂的声明（<T extends Enum<T> & Operation> Class<T>）确保了 Class 对象既是枚举又是 Operation 的子类，
     * 这正是遍历元素和执行每个元素相关联的操作时所需要的。
     */
    private static <T extends Enum<T> & Operation> void test(
            Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }

    /**
     * 第二种方式是传递一个 Collection<? extends Operation>，这是一个限定通配符类型（详见第 31 条），而不是传递了一个 class 对象：
     */
    private static void test(Collection<? extends Operation> operations,
                             double x, double y) {
        for (Operation op : operations) {
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }

    public static void main(String[] args) {
        // 方式1:
        double x = 2.0;
        double y = 4.0;
        test(ExtendedOperation.class, x, y);
        // 方式2:
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }


}
