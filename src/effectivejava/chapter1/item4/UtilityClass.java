package effectivejava.chapter1.item4;

/**
 * 通过私有构造方法阻止实例化
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class UtilityClass {

    /**
     * suppress default constructor for non instantiability
     */
    private UtilityClass() {
        // 抛出异常非必须，但是可以防止在类的内部无意调用构造方法实例化
        // 如果这样跑异常的话，最好在注释中说明原因
        throw new AssertionError("can not instance");
    }

    // other code ...
}
