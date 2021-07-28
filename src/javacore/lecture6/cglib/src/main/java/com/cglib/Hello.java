package com.cglib;

/**
 * cglib 演示类
 *
 * @author Alan Yin
 * @date 2019/12/26
 */

public class Hello {

    public String sayHello(String name) {
        System.out.println("Hello," + name);
        return "Hello," + name;
    }

}
