package effectivejava.chapter1.item1;

/**
 * @author yinxing
 * @date 2019/8/12
 */

public interface Temp {

    /**
     * java 8 开始，允许接口有静态方法
     *
     * @return
     */
    static String testStaticMethod() {
        return "hello";
    }
}
