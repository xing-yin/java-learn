package javacore.lecture2.demo2;

/**
 * 这类错误发生在运行时，通常是编译通过，但当运行时使用new参数该类对象时，找不到类的定义造成，
 * 此异常通常是由于一个类中引用了另外的一个类，而被引用的类没有被classLoader找到。
 * <p>
 * 如在编译好 User 类后删除 user.class时再运行。
 *
 * @author Alan Yin
 * @date 2020/4/2
 */

public class NotClassDefFoundErrorDemo {

    public static void main(String[] args) {
        User user = new User("jack", 20);
        System.out.println(user.getName());
    }
}
