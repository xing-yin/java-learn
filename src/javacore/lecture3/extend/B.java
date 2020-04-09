package javacore.lecture3.extend;

/**
 * @author Alan Yin
 * @date 2020/4/9
 */

public class B {

    public B() {
        System.out.println("B()");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("B finalize");
    }

}
