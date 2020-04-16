//package com.cglib;
//
///**
// * CGLIB动态代理
// *
// * @author Alan Yin
// * @date 2019/12/26
// */
//
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
///**
// * 1. 首先实现一个MethodInterceptor，方法调用会被转发到该类的intercept()方法。
// */
//public class MyMethodInterceptor implements MethodInterceptor {
//
//    @Override
//    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        System.out.println("intercept param is " + Arrays.toString(args));
//        before();
//        Object result = methodProxy.invokeSuper(obj, args);
//        // 这里可以实现增强的逻辑处理
//        after();
//        return result;
//    }
//
//    private void after() {
//    }
//
//    private void before() {
//    }
//
//}
