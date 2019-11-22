package effectivejava.chapter3.item16;

/**
 * 正例:通过set/get方法来封装数据
 *
 * @author yinxing
 * @date 2019/8/16
 */

/**
 * 通过set/get方法来封装数据
 * Encapsulation of data by accessor methods and mutators
 */
public class Point {

    private double x;

    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 如果一个类在其包之外是可访问的，则提供访问方法(set/get)来保留更改类内部表示的灵活性。
     */
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}

/**
 * 总之，公共类不应该暴露`可变`属性。
 * 公共类暴露不可变属性的危害虽然仍然存在问题，但其危害较小,依然不建议这么做。
 * 然而，有时需要包级私有或私有内部类来暴露属性，无论此类是否是可变的。
 */