package effectivejava.chapter3.item22;

/**
 * 反例：一种失败的接口就是所谓的常量接口（constant interface）。
 * <p>
 * 这样的接口不包含任何方法; 它只包含静态 final 属性，每个输出一个常量。
 * 使用这些常量的类实现接口，以避免需要用类名限定常量名。
 *
 * @author yinxing
 * @date 2019/8/26
 */

/**
 * Constant interface antipattern - do not use!
 */
public class PhysicalConstants {

    /**
     * Avogadro's number (1/mol)
     * 阿伏伽德罗数
     */
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    /**
     * Boltzmann constant (J/K)
     */
    static final double BOLZMANN_CONSTANT = 1.380_648_52e-23;

    /**
     * Mass of the electron (kg)
     */
    static final double ELECTRON_MASS = 9.109_383_56e-31;

}

/**
 * 问题:
 * 常量接口模式是对接口的糟糕使用。
 * <p>
 * a.类在内部使用一些常量，完全属于实现细节。实现一个常量接口会导致这个实现细节泄漏到类的导出 API 中。
 *  (对类的用户来说，类实现一个常量接口是没有意义的。事实上，它甚至可能使他们感到困惑。)
 * b.更糟糕的是，它代表了一个承诺：如果在将来的版本中修改了类，不再需要使用常量，那么它仍然必须实现接口，以确保二进制兼容性。
 *  (如果一个非 final 类实现了常量接口，那么它的所有子类的命名空间都会被接口中的常量所污染。)
 */