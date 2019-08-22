package effectivejava.chapter1.item14;

/**
 * 在 Java 7 中，静态比较方法被添加到 Java 的所有包装类中,比如 Integer.compare(), Double.compare()；
 * 在 compareTo 方法中使用关系运算符「<」和「>」是冗长且容易出错的，不再推荐。
 *
 * @author yinxing
 * @date 2019/8/22
 */

public class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {

    public CaseInsensitiveString() {
    }

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare("string1", "string2");
    }


}
