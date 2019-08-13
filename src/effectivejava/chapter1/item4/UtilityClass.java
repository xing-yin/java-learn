package effectivejava.chapter1.item4;

/**
 * @author yinxing
 * @date 2019/8/13
 */

public class UtilityClass {

    /**
     * suppress default constructor for noninstantiability
     */
    private UtilityClass() {
        throw new AssertionError("can not instance");
    }

    // other code ...
}
