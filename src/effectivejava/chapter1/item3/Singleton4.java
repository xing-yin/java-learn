package effectivejava.chapter1.item3;

/**
 * @author yinxing
 * @date 2019/8/13
 */

public class Singleton4 {

    private static volatile Singleton4 uniqueInstance;

    private Singleton4() {
    }

    public static Singleton4 getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton4.class) {
                if (uniqueInstance == null) {
                    return new Singleton4();
                }
            }
        }
        return uniqueInstance;
    }
}
