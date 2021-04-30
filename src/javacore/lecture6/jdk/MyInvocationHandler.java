package javacore.lecture6.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Alan Yin
 * @date 2019/12/26
 */

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("sayHello".equals(methodName)) {
            System.out.println("change method");
            return null;
        }
        System.out.println("invoke method");
        Object result = method.invoke(target, args);
        return result;
    }
}
