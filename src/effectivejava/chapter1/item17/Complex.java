package effectivejava.chapter1.item17;

/**
 * @author yinxing
 * @date 2019/8/19
 */

/**
 * Immutable complex number class
 * 不可变的复数类
 */
public final class Complex {

    /**
     * 不可变类应鼓励客户端尽可能重用现有的实例。
     * - 一个简单的方法是为常用的值提供公共的静态 final 常量。
     * - 更好的方式是提供静态工厂。
     */
    public static final Complex ZERO = new Complex(0, 0);

    public static final Complex ONE = new Complex(1, 1);

    public static final Complex TWO = new Complex(2, 2);

    /**
     * 留意使用 final 修饰
     * 实数位
     */
    private final double re;

    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * 获取实部和虚部的方法
     */
    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    /**
     * 加减乘除方法
     * 注意算术运算如何创建并返回一个新的 Complex 实例，而不是修改这个实例。
     * 这种模式被称为函数式方法，因为方法返回将操作数应用于函数的结果，而不修改它们。
     */
    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
    }

    public Complex divideBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Complex)) {
            return false;
        }

        Complex c = (Complex) o;
        // 思考一下我们为什么要实现 instanceof: 见 item 10
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + "+" + im + "i)";
    }
}
