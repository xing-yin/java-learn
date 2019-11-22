package effectivejava.chapter3.item17;

/**
 * 为了保证不变性，一个类不得允许子类化。 这可以通过使类用 final 修饰，
 * 但是还有另外一个更灵活的选择,而不是使不可变类设置为 final，可以使其所有的构造方法私有或包级私有，并添加公共静态工厂，而不是公共构造方法。
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

    /**
     * 这种静态工厂的优势：
     *
     * a.这种方法往往是最好的选择，最灵活的，因为它允许使用多个包级私有实现类。
     *
     * b.除了允许多个实现类的灵活性以外，这种方法还可以通过改进静态工厂的对象缓存功能来调整后续版本中类的性能
     */
}
