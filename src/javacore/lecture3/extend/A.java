package javacore.lecture3.extend;

/**
 * @author Alan Yin
 * @date 2020/4/9
 */

public class A {

    public A() {
        System.out.println("A()");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("A finalize");
    }

    B b;
    C c;

}
