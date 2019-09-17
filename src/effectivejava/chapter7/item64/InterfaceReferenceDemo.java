package effectivejava.chapter7.item64;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class InterfaceReferenceDemo {

    /**
     * Good - uses interface as type
     */
    Set<String> set1 = new LinkedHashSet<>(6);

    /**
     * Bad - uses class as type!
     */
    LinkedHashSet<String> set2 = new LinkedHashSet<>(6);

    public void tset() {
        // 可以更灵活的切换实现
        set1 = new HashSet<String>(6);
    }
}
