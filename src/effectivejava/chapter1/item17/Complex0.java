package effectivejava.chapter1.item17;

/**
 * 为了保证不变性，一个类不得允许子类化。 这可以通过使类用 final 修饰，但是还有另外一个更灵活的选择,
 * 而不是使不可变类设置为 final，可以使其所有的构造方法私有或包级私有，并添加公共静态工厂，而不是公共构造方法
 *
 * @author yinxing
 * @date 2019/8/19
 */

/**
 * Immutable complex number class
 * 不可变的复数类
 */
public final class Complex0 {

    private final double re;

    private final double im;

    public Complex0(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    // other code ...
}
