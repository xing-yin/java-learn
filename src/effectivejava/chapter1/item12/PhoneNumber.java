package effectivejava.chapter1.item12;

/**
 * @author yinxing
 * @date 2019/8/20
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
    public String toString() {
        return "PhoneNumber:" + areaCode +
                "-" + prefix +
                "-" + lineNum;
    }

    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber(1, 22, 333);
        System.out.println(pn);
    }
}
