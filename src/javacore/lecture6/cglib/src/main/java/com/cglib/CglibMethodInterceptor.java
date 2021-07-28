package com.cglib;

/**
 * CGLIB动态代理
 *
 * @author Alan Yin
 * @date 2019/12/26
 */

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 1. 首先实现一个MethodInterceptor，方法调用会被转发到该类的intercept()方法。
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("intercept param is " + Arrays.toString(args));
        System.out.println("before===============" + method);
        // 这里可以实现增强的逻辑处理
        Object result = methodProxy.invokeSuper(obj, args);
        // 这里可以实现增强的逻辑处理
        System.out.println("after===============" + method);
        return result;
    }

}
