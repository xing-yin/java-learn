package effectivejava.chapter1.item6;

/**
 * 重用不可变对象，而不是新建一个对象
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class ImmutableClass {

    /**
     * 反例:程序每次调用时会创建一个新的实例，实际上对象不可变
     */
    String s1 = new String("new string");

    /**
     * 正例:只有一个实例，在同一虚拟机中可以被重用
     */
    String s2 = "recommend";

    /**
     * 如果知道对象不会被修改，还可以重用可变对象
     * (当一个对象是不可变的时，很明显它可以被安全地重用)
     */

}
