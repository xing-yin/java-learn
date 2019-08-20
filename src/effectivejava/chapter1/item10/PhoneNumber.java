package effectivejava.chapter1.item10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public final class PhoneNumber {

    private final int areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "aera code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 999, "line num");
    }

    private static int rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ":" + val);
        }
        return (int) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber pn = (PhoneNumber) o;
        return pn.areaCode == areaCode
                && pn.prefix == prefix
                && pn.lineNum == lineNum;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> phoneNumberMap = new HashMap(2);
        phoneNumberMap.put(new PhoneNumber(707, 869, 539), "jack");
        /**
         * 情形1：重写equals, 没有重写 hashcode: 违反了相等的对象必须具有相等的哈希码（ hash codes）。
         * 根据类的 equals 方法，两个不同的实例可能在逻辑上是相同的，但是对于 Object 类的 hashCode 方法，它们只是两个没有什么共同之处的对象。
         */
        System.out.println(phoneNumberMap.get("jack"));
    }

}
