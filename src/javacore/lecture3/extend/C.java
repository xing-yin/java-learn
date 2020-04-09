package javacore.lecture3.extend;

/**
 * @author Alan Yin
 * @date 2020/4/9
 */

public class C {

    public C() {
        System.out.println("C()");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("C finalize");
    }

}
