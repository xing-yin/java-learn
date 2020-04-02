package javacore.lecture1.demo1;

/**
 * 新的编译方式，即所谓的AOT(Ahead-of-Time Compilation)，直接将字节码编译成机器代码
 *
 * @author Alan Yin
 * @date 2020/4/1
 */

public class AOTComplieDemo {

    /**
     * 避免了JIT预热等各方面的开销，比如Oracle JDK 9就引入了实验性的AOT特性，并且增加了新的jaotc工具。
     * 利用下面的命令把某个类或者某个模块编译成为AOT库
     */
    // jaotc --output libHelloWorld.so HelloWorld.class
    // jaotc --output libjava.base.so --module java.base

    /**
     * 然后，在启动时直接指定就可以了。
     */
    // java -XX:AOTLibrary=./libHelloWorld.so,./libjava.base.so HelloWorld

}
