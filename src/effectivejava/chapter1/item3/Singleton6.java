package effectivejava.chapter1.item3;

/**
 * 枚举实现【全场最佳】
 * <p>
 * 线程安全、可以抵御反射攻击、可以正常序列化/反序列化
 *
 * @author yinxing
 * @date 2019/8/13
 */

public enum Singleton6 {

    /**
     * 单例
     */
    UNIQUE_INSTANCE;
}
