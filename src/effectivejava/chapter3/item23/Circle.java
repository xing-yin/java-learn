package effectivejava.chapter3.item23;

/**
 * @author yinxing
 * @date 2019/8/26
 */

public class Circle implements Figure0 {

    final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * (radius * radius);
    }
}
