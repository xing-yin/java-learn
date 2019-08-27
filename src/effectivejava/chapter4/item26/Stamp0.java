package effectivejava.chapter4.item26;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 正例: Parameterized collection type - typesafe
 *
 * @author yinxing
 * @date 2019/8/27
 */

public class Stamp0 {


    /**
     * My stamp collection. Contains only Stamp instances.
     * 参数化集合类型-类型安全
     */
    private static final Collection<Stamp0> stamps = new ArrayList();

    public static Collection test() {
        // incompatible types: Coin cannot be converted
        // 不满足条件的元素不允许插入
//        stamps.add(new Coin());
        return stamps;
    }

}
