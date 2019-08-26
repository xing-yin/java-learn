package effectivejava.chapter3.item23;

/**
 * @author yinxing
 * @date 2019/8/26
 */

public class Rectangle implements Figure0 {

    private final double length;

    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}
