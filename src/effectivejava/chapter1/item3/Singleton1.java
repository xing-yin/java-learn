package effectivejava.chapter1.item3;

/**
 * 线程不安全
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class Singleton1 {

    private static Singleton1 uniqueInstance;

    private Singleton1() {
    }

    public static Singleton1 getSingletonInstance() {
        if (uniqueInstance == null) {
            return new Singleton1();
        }
        return uniqueInstance;
    }
}
