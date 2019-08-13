package effectivejava.chapter1.item3;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AccessibleObject.setAccessible() 方法测试：访问 private 修饰的变量/方法
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class AccessibleTest {

    private int id=0;

    private AccessibleTest() {
        System.out.println("construct method");
    }

    public static void main(String[] args) {
        AccessibleTest at =new AccessibleTest();
        Field[] fields = at.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields,true);
        System.out.println("fields" +fields.toString());
        try {
            System.out.println(fields[0].toString() + " = " + fields[0].get(at));
            fields[0].setInt(at,150);
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
