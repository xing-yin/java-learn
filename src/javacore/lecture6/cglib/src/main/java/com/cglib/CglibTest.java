//package com.cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//
///**
// * @author Alan Yin
// * @date 2019/12/26
// */
//
//public class CglibTest {
//
//    /**
//     * 2. 然后在需要使用 Hello 的时候，通过CGLIB动态代理获取代理对象
//     */
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Hello.class);
//        enhancer.setCallback(new MyMethodInterceptor());
//
//        Hello hello = (Hello) enhancer.create();
//        System.out.println(hello.sayHello("yx"));
//    }
//}
