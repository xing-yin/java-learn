package effectivejava.chapter3.item22;

/**
 * 正例: 使用类定义常量
 *
 * @author yinxing
 * @date 2019/8/26
 */

/**
 * Constant utility class
 * 实用工具类
 */
public class PhysicalConstants0 {

    /**
     * 【关键】
     * prevents instantiation
     */
    private PhysicalConstants0() {
    }

    /**
     * 从 Java 7 开始，合法的下划线对数字字面量的值没有影响，但是如果使用得当的话可以使它们更容易阅读。
     * 无论是固定的浮点数，如果他们包含五个或更多的连续数字，考虑将`下划线`添加到数字字面量中。
     * 对于底数为 10 的数字，无论是整型还是浮点型的，都应该用下划线将数字分成三个数字组，表示一千的正负幂。
     */
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    static final double BOLZMANN_CONSTANT = 1.380_648_52e-23;

    static final double ELECTRON_MASS = 9.109_383_56e-31;

    public static void main(String[] args) {
        System.out.println(PhysicalConstants0.AVOGADROS_NUMBER);
    }
}

/**
 *  如果你想导出常量，有几个合理的选择方案。
 *  a. 如果常量与现有的类或接口紧密相关，则应将其添加到该类或接口中。
 *     (例如，所有数字基本类型的包装类，如 Integer 和 Double，都会导出 MIN_VALUE 和 MAX_VALUE 常量。)
 *  b. 如果常量最好被看作枚举类型的成员，则应该使用枚举类型（详见第 34 条）导出它们。
 *  c. 否则，你应该用一个不可实例化的工具类来导出常量（详见第 4 条）。
 */

/**
 * 总之，接口只能用于定义类型。它们不应该仅用于导出常量。
 */