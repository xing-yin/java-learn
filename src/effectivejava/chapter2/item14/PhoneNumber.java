package effectivejava.chapter2.item14;

import java.util.Comparator;

/**
 * 如果一个类有多个重要的属性，那么比较他们的顺序是至关重要的。
 * 从最重要的属性开始，逐步比较所有的重要属性。如果比较结果不是零（零表示相等），则表示比较完成; 直接返回结果。
 * 如果最重要的字段是相等的，比较下一个重要的属性，依此类推，直到找到不相等的属性或比较剩余不那么重要的属性。
 *
 * @author yinxing
 * @date 2019/8/22
 */

public class PhoneNumber implements Comparable<PhoneNumber> {

    private final int areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "aera code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 999, "line num");
    }

    private int rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ":" + val);
        }
        return (int) val;
    }

    /**
     * 方式1:[最佳]
     * Multiple-field `Comparable` with primitive fields
     * 多属性时优先比较最重要的属性
     */
    @Override
    public int compareTo(PhoneNumber pn) {
        int result = Integer.compare(areaCode, pn.areaCode);
        if (result == 0) {
            result = Integer.compare(prefix, pn.prefix);
            if (result == 0) {
                result = Integer.compare(lineNum, pn.lineNum);
            }
        }
        return result;
    }

    /**
     * 方式2:性能低于方式1，lambda表达式更简洁
     * Comparable with comparator construction methods
     * 利用 jdk1.8 中的 Comparator 接口的比较器方法: 性能比上面要差一点
     */
//    private static final Comparator<PhoneNumber> COMPARATOR =
//            Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
//                    .thenComparingInt((PhoneNumber pn) -> pn.prefix)
//                    .thenComparingInt((PhoneNumber pn) -> pn.lineNum);
//
//    @Override
//    public int compareTo(PhoneNumber pn) {
//        return COMPARATOR.compare(this, pn);
//    }

    /**
     * 反例:
     * 它可能会导致整数最大长度溢出和 IEEE 754 浮点运算失真的危险[JLS 15.20.1,15.21.1]。 此外，由此产生的方法不可能比使用上述技术编写的方法快得多。
     * 有时，你可能会看到 compareTo 或 compare 方法依赖于两个值之间的差值：
     * 如果第一个值小于第二个值，则为负；如果两个值相等则为零，如果第一个值大于，则为正值。
     */
//    static Comparator<Object> hashCodeOrder0 = new Comparator<Object>() {
//        @Override
//        public int compare(Object o1, Object o2) {
//            return o1.hashCode() - o2.hashCode();
//        }
//    };

    /**
     * 反例改写1:使用静态的 compare 方法
     */
    static Comparator<Object> hashCodeOrder1 = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    };

    /**
     * 反例改写2:使用 Compactor 方法
     * Comparator based on Comparator construction method
     */
    static Comparator<Object> hashCodeOrder2 = Comparator.comparingInt(o -> o.hashCode());

}
