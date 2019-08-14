package effectivejava.chapter1.item6;

/**
 * 重用不可变对象
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class ImmutableClass {

    // 反例:程序每次调用时会创建一个新的实例
    String s1 = new String("new string");

    // 正例:只有一个实例，在同一虚拟机中可以被重用
    String s2 = "recommend";

}
