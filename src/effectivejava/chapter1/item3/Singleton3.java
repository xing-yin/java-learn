package effectivejava.chapter1.item3;

/**
 * 线程安全
 * <p>
 * 没有使用时而且初始化消耗资源较多时造成浪费
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class Singleton3 {

    private static Singleton3 uniqueInstance = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getSingletonInstance() {
        return uniqueInstance;
    }
}
