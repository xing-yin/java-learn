package effectivejava.chapter1.item3;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Alan Yin
 * @date 2020/4/15
 */

public class MainDemo {

    public static void main(String[] args) {
        AccessibleTest at =new AccessibleTest();
        Field[] fields = at.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields,true);
        System.out.println("fields length: " +fields.length);
        try {
            System.out.println(fields[0].toString() + " = " + fields[0].get(at));
            fields[0].setInt(at,150);
            // at.id = 200; // not allowed,私有属性
            System.out.println(fields[0].toString() + " = " + fields[0].get(at));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Method[] methods = at.getClass().getDeclaredMethods();

        Constructor[] constructors = at.getClass().getConstructors();
        AccessibleObject.setAccessible(constructors,true);
        System.out.println(constructors[0].toString() + " = " + constructors[0].getName());
    }
}
