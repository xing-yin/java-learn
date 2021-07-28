package javacore.lecture6.jdk;

/**
 * @author Alan Yin
 * @date 2019/12/26
 */

public interface HelloInterface {

    /**
     * 代理的目标方法
     */
    void sayHello();

    /**
     * 未被代理处理的方法
     */
    void noProxyMethod();
}
