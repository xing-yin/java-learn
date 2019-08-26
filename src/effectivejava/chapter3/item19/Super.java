package effectivejava.chapter3.item19;

/**
 * 反例: 构造方法绝不能直接或间接调用可重写的方法。如果违反这个规则，将导致程序失败。
 * @author yinxing
 * @date 2019/8/23
 */

public class Super {

    /**
     * Broken - constructor invokes an overridable method
     * 构造方法引用了一个重写的方法
     */
    public Super() {
        overrideMethod();
    }

    public void overrideMethod() {
    }

}
