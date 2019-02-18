package CSNote.basis.super_example;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class SuperExtendExample extends SuperExample {

    private int z;

    public SuperExtendExample(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void func() {
        super.func();
        System.out.println("SuperExtendExample.func()");
    }

    @Override
    public double override() {
        return super.override();
    }

    public static void main(String[] args) {
//        SuperExample superExample = new SuperExtendExample(1,2,3);
//        superExample.func();

    }
}
