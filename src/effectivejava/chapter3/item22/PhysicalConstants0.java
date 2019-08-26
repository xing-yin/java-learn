package effectivejava.chapter3.item22;

/**
 * 正例:总之，接口只能用于定义类型。 它们不应该仅用于导出常量。
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
     * 无论是固定的浮点数，如果他们包含五个或更多的连续数字，考虑将下划线添加到数字字面量中。
     * 对于底数为 10 的数字，无论是整型还是浮点型的，都应该用下划线将数字分成三个数字组，表示一千的正负幂。
     */
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    static final double BOLZMANN_CONSTANT = 1.380_648_52e-23;

    static final double ELECTRON_MASS = 9.109_383_56e-31;

    public static void main(String[] args) {
        System.out.println(PhysicalConstants0.AVOGADROS_NUMBER);
    }
}
