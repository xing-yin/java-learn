package javacore.lecture1.demo2;

/**
 * 1.定义需要加载的类:
 * <p>
 * 需要加载的Hello类中的反射调用的方法要用 static 修饰，这样 invoke 的时候第一个参数才可以使用null关键字代替，否则需要创建一个对应的类实例。
 *
 * @author Alan Yin
 * @date 2020/4/1
 */

/**
 * 为了能够实现类加载，并展示效果，定义一个Hello类，再为其定义一个sayHello()方法，加载Hello类之后，调用它的sayHello()方法。
 */
public class Hello {

    public static void sayHello() {
        System.out.println("Hello!This is my classLoader");
    }

}
