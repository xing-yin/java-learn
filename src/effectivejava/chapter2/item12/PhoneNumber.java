package effectivejava.chapter2.item12;

/**
 * 类实现 Cloneable 接口
 *
 * @author yinxing
 * @date 2019/8/20
 */
public final class PhoneNumber implements Cloneable {

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

    /**
     * 因为 Java 支持协变返回类型。 换句话说，重写方法的返回类型可以是重写方法的返回类型的子类。
     * 优点:不需要客户端自己转换。
     *
     * @return
     */
    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            // can not happen
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber(1, 22, 333);
        pn.clone();
    }

}
