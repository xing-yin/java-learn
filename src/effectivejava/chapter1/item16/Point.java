package effectivejava.chapter1.item16;

/**
 * 正例:通过set/get方法来封装数据
 * <p>
 * 【结论】公共类不应该暴露可变属性。 公共累暴露不可变属性的危害虽然仍然存在问题，但其危害较小。
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
