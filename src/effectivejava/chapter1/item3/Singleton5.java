package effectivejava.chapter1.item3;

/**
 * 静态内部类
 * <p>
 * 懒加载
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class Singleton5 {

    private Singleton5() {
    }

    private static class SingletonHolder {
        private static Singleton5 uniqueInstance = new Singleton5();
    }

    public static Singleton5 getSingletonInstance() {
        return SingletonHolder.uniqueInstance;
    }
}

