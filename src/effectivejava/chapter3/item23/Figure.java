package effectivejava.chapter3.item23;

/**
 * 反例:标签类 - 远不如类层次结构
 * <p>
 * 标签类是冗长的，容易出错的，而且效率低下:
 * a.充斥着杂乱无章的样板代码
 * b. 可读性更差，多个实现在一个类中混杂在一起
 * c.内存使用增加，因为实例负担属于其他风格不相关的属性
 * d.可扩展性差
 *
 * @author yinxing
 * @date 2019/8/26
 */

public class Figure {

    enum Shape {
        /**
         * shape
         */
        RECTANGLE, CIRCLE
    }

    final Shape shape;

    /**
     * These fields are used only if shape is RECTANGLE
     */
    private double length;

    private double width;

    /**
     * This field is used only if shape is CIRCLE
     */
    private double radius;

    /**
     * Construct for rectangle
     *
     * @param length
     * @param width
     */
    public Figure(double length, double width) {
        this.shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    /**
     * Construct for circle
     *
     * @param redius
     */
    public Figure(double redius) {
        this.shape = Shape.CIRCLE;
        this.radius = redius;
    }

    public double area() {
        switch (shape) {
            case CIRCLE:
                return Math.PI * (radius * radius);
            case RECTANGLE:
                return length * width;
            default:
                throw new AssertionError(shape);
        }
    }
}
