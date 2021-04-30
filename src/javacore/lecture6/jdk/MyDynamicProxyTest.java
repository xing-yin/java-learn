package javacore.lecture6.jdk;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理的一个简单例子:
 * 下面只是加了一句print，在生产系统中，我们可以轻松扩展类似逻辑进行诊断、限流等。
 *
 * @author Alan Yin
 * @date 2019/12/26
 */

public class MyDynamicProxyTest {

    public static void main(String[] args) {
        HelloInterface hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        HelloInterface proxyInstance = (HelloInterface) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(),
                handler);
        // 代理调用方法
        proxyInstance.sayHello();
    }
}
