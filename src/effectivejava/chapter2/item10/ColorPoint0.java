package effectivejava.chapter2.item10;

import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public class ColorPoint0 {

    private final Color color;

    // 使用组合而不是继承
    private final Point point;

    public ColorPoint0(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    /**
     * return point-view of this color point
     */
    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint0)) {
            return false;
        }
        ColorPoint0 cp = (ColorPoint0) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }

    public static void main(String[] args) {
        ColorPoint0 p1 = new ColorPoint0(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint0 p3 = new ColorPoint0(1, 2, Color.GREEN);
        System.out.println("p1 equals p2");
        // false
        System.out.println(p1.equals(p2));
        // false
        System.out.println(p2.equals(p1));
        System.out.println("p2 equals p3");
        // false
        System.out.println(p2.equals(p3));
        // fals
        System.out.println(p3.equals(p2));
        System.out.println("p1 equals p3");
        // false
        System.out.println(p1.equals(p3));
        // false
        System.out.println(p3.equals(p1));
    }
}
