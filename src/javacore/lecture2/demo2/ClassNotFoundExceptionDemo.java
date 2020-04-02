package javacore.lecture2.demo2;

/**
 * NoClassDefFoundError 和 ClassNotFoundException 有什么区别？
 *
 * @author Alan Yin
 * @date 2020/4/2
 */

/**
 * 这个问题是和java 类的加载相关的。
 * <p>
 * 类加载的时候先要把编译好的类文件（.class ，jar包等）加载JVM管理的方法区当中，这个过程叫做加载，如果这个过程中没找类文件就会出现 ClassNotFoundException。
 * 如果加载成功之后，会有一个该类的类对象（class对象）。
 * <p>
 * 运行时，方法调用或new对象时，在内存当中没有找到这个类对象，就会出现 NoClassDefFoundError。
 */
public class ClassNotFoundExceptionDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 错误
        Class.forName("ClassNotFoundExceptionDemo");
        // 正确
        // Class.forName("javacore.lecture2.demo2.ClassNotFoundExceptionDemo");
    }
}
