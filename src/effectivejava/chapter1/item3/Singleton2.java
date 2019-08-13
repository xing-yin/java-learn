package effectivejava.chapter1.item3;

/**
 * 线程安全，但开销比较大
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class Singleton2 {

    private static Singleton2 uniqueSingleton2;

    private Singleton2() {
    }

    public static synchronized Singleton2 getUniqueSingleton2() {
        if (uniqueSingleton2 == null) {
            return new Singleton2();
        }
        return uniqueSingleton2;
    }

}
