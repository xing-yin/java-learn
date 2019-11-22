package effectivejava.chapter3.item16;

/**
 * @author yinxing
 * @date 2019/11/21
 */

public class PrivetInnerClass {

    private int z;

    public PrivetInnerClass(int z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void testGetY() {
        InnerClassDemo demo = new InnerClassDemo();
        z = demo.y;
    }

    private class InnerClassDemo {
        /**
         * 有时需要包级私有或私有内部类来暴露属性，无论此类是否是可变的。
         */
        public int x;

        public int y;
    }
}
