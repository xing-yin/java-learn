package effectivejava.chapter3.item22;

/**
 * 反例：一种失败的接口就是所谓的常量接口（constant interface）。
 * <p>
 * 这样的接口不包含任何方法; 它只包含静态 final 属性，每个输出一个常量。 使用这些常量的类实现接口，以避免需要用类名限定常量名。
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
