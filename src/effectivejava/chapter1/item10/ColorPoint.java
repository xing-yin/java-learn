package effectivejava.chapter1.item10;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /**
     * 反例1:违反了对称性
     * 当你比较 Point 对象和 ColorPoint 对象时，可以会得到不同的结果，反之亦然。
     * 前者的比较忽略了颜色属性，而后者的比较会一直返回 false，因为参数的类型是错误的。
     */
//    @Override
    public boolean equals1(Object o) {
        if (!(o instanceof ColorPoint)) {
            return false;
        }
        return super.equals(o) && ((ColorPoint) o).color == color;
    }

    /**
     * 反例2
     * 这种方法确实提供了对称性，但是丧失了传递性
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        // if o is normal point,do a color-blind comparison
        // 如果 o 是一个 Point，不做颜色比较
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }

        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((ColorPoint) o).color == color;
    }

    /**
     * 正例1：见 ColorPoint0
     * 虽然没有令人满意的方法来继承一个可实例化的类并添加一个值组件，但是有一个很好的变通方法：按照条目 18 的建议，“优先使用组合而不是继承”。
     * 取代继承 Point 类的 ColorPoint 类，可以在 ColorPoint 类中定义一个私有 Point 属性，和一个公共的试图（view）（详见第 6 条）方法，
     * 用来返回具有相同位置的 ColorPoint 对象。
     *
     */

    public static void main(String[] args) {
        /**
         * 情形1：equals1
         */
//        Point p = new Point(1, 2);
//        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
//        // true
//        System.out.println(p.equals(cp));
//        // false
//        System.out.println(cp.equals(p));
        /**
         * 情形2：equals2
         * p1.equals(p2) 和 p2.equals(p3) 返回了 true，但是 p1.equals(p3) 却返回了 false
         * 因为前两个比较都是不考虑颜色信息的，而第三个比较时却包含颜色信息
         */
//        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
//        Point p2 = new Point(1, 2);
//        ColorPoint p3 = new ColorPoint(1, 2, Color.GREEN);
//        System.out.println("p1 equals p2");
//        // true
//        System.out.println(p1.equals(p2));
//        // true
//        System.out.println(p2.equals(p1));
//        System.out.println("p2 equals p3");
//        // true
//        System.out.println(p2.equals(p3));
//        // true
//        System.out.println(p3.equals(p2));
//        System.out.println("p1 equals p3");
//        // false
//        System.out.println(p1.equals(p3));
//        // false
//        System.out.println(p3.equals(p1));

    }
}
