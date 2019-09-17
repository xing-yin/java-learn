package effectivejava.chapter7.item62;

/**
 * 不够好例子:
 * 通过用一个不可伪造的键（有时称为 capability）替换字符串来修复 ThreadLocalDemo0
 *
 * @author yinxing
 * @date 2019/9/17
 */

public class ThreadLocalDemo1 {

    /**
     * Noninstantiable
     */
    private ThreadLocalDemo1() {
    }

    /**
     * Capability
     */
    public static class Key {
        Key() {
        }
    }

    /**
     * Generates a unique, unforgeable key
     */
    public static Key getKey() {
        return new Key();
    }

    public static void set(Key key, Object value) {
    }

    public static Object get(Key key) {
        return null;
    }

}
