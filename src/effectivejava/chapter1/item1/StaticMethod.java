package effectivejava.chapter1.item1;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author yinxing
 * @date 2019/8/12
 */

public class StaticMethod {

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    // 提供静态工厂方法而不是公共构造方法的优缺点

    /**
     * 优点1:与构造方法不同，有名字
     */
    public void temp1() {
        Random random = new Random();
        BigInteger.probablePrime(2, random);
    }

    /**
     * 优点2:不需要每次调用时创建一个新的对象(特别是代码比较大时)
     */

    /**
     * 优点3:与构造方法不同，可以返回其返回类型大任何子类型的对象
     * （java 8开始，允许接口包含静态方法）
     */

    /**
     * 优点4:可以根据输入参数类型的不同而不同
     */
    public void temp2() {
        // EnumSet只有静态工厂方法
        // 在 OpenJDK 实现中，它们根据底层枚举类型的大小返回两个子类中的一个的实例：
        // 1. 大多数枚举类型具有 64 个或更少的元素，静态工厂将返回一个 RegularEnumSet 实例， 底层是long 类型；
        // 2.如果枚举类型具有六十五个或更多元素，则工厂将返回一个 JumboEnumSet 实例，底层是long 类型的数组。
    }

    /**
     * 缺点1:没有公共或受保护构造方法的类不能被子类化（即无法被继承）
     */

    /**
     * 缺点2:程序员很难找到它们
     */

}
