package effectivejava.chapter7.item61;

import java.util.Comparator;

/**
 * @author yinxing
 * @date 2019/9/16
 */

public class AutoBoxDemo {

    /**
     * 反例:broken compator
     * 有问题的比较器
     * 将 == 操作符应用于包装类型几乎都是错误的
     */
    static Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

    static Comparator<Integer> naturalOrder2 = (iBoxed, jBoxed) -> {
        int i = iBoxed, j = jBoxed;
        return (i < j) ? -1 : (i == j ? 0 : 1);
    };

    public static void main(String[] args) {
        System.out.println(naturalOrder.compare(new Integer(20), new Integer(20)));
        System.out.println(naturalOrder2.compare(new Integer(20), new Integer(20)));
    }
}
