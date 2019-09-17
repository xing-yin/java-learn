package effectivejava.chapter7.item62;

/**
 * 反例:字符串不能很好地替代 capabilities
 * <p>
 * 这种方法的问题在于，字符串键表示线程本地变量的共享全局名称空间。
 * 为了使这种方法有效，客户端提供的字符串键必须是唯一的：如果两个客户端各自决定为它们的线程本地变量使用相同的名称，
 * 它们无意中就会共享一个变量，这通常会导致两个客户端都失败。而且，安全性很差。
 *
 * @author yinxing
 * @date 2019/9/17
 */

/**
 * Broken - inappropriate use of string as capability!
 */
public class ThreadLocalDemo0 {

    /**
     * Noninstantiable
     */
    private ThreadLocalDemo0() {
    }

    /**
     * Sets the current thread's value for the named variable.
     */
    public static void set(String key, Object value) {
    }

    /**
     * Returns the current thread's value for the named variable.
     */
    public static Object get(String key) {
        return null;
    }

}
