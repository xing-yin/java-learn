package effectivejava.chapter1.item10;

import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public class CaseInsensitiveString {

    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    /**
     * 反例: Broken - violates symmetry!
     * 违反了对称性
     * <p>
     * 危害: 没有哪个类是孤立存在的。一个类的实例常常被传递给另一个类的实例。
     * 许多类，包括所有的集合类，都依赖于传递给它们遵守 equals 约定的对象。一旦源头出错，出现问题时就难以定位排查。
     */
//    @Override
    public boolean equals1(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        }
        /**
         * One-way interoperability
         * 单向互操作
         */
        if (o instanceof String) {
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }

    /**
     * 正例：只需删除上面 equals 方法中与 String 类相互操作的恶意尝试。
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof CaseInsensitiveString) &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }

    // other code ...


    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Test");
        String s = "test";

        // true
        System.out.println(cis.equals(s));
        // false
        System.out.println(s.equals(cis));
    }

}
