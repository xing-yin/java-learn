package javacore.lecture6.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Alan Yin
 * @date 2019/12/26
 */

public class MyInvocationHandler implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 构造方法
     *
     * @param target
     */
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("sayHello".equals(methodName)) {
            // 比方说，mybaitis 中的 PooledConnection 利用 jdk 动态代理重新实现了 close 方法
            System.out.println("change method");
            return null;
        }
        System.out.println("invoke method");
        Object result = method.invoke(target, args);
        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }

}
