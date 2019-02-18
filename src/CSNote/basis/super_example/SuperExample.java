package CSNote.basis.super_example;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class SuperExample {

    protected int x;
    protected int y;

    public SuperExample(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void func(){
        System.out.println("SuperExample.func()");
    }

    protected double override(){
        return 0d;
    }
}
