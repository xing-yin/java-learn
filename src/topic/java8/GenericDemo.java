package topic.java8;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 泛型演示
 *
 * @author Alan Yin
 * @date 2021/7/28
 */

public class GenericDemo implements Serializable {

    public static void main(String[] args) {
        Demo demo = new Demo();
        Class clazz = demo.getClass();
        // getSuperclass() 获得该类的父类
        System.out.println("getSuperclass()： " + clazz.getSuperclass());
        // getGenericSuperclass() 获得带有泛型的父类
        // Type 是 Java 中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type type = clazz.getGenericSuperclass();
        System.out.println("getGenericSuperclass()： " + type);
        // ParameterizedType 参数化类型，即泛型
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // getActualTypeArguments() 获取参数化类型
        Class c = (Class) parameterizedType.getActualTypeArguments()[0];
        System.out.println("getActualTypeArguments()[0]： " + c);
    }

    public static class Demo extends ArrayList<Integer> {

    }
}
