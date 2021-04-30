package core.chap3;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class StringDemo {

    private static final String STRING_ONE = "hi";
    private static final String STRING_TWO = "hi";

    public static void main(String[] args) {
        String s1 = String.join("|", "a", "b", "c");
        System.out.println("s1:" + s1);

        String s2 = "hello";
        String s3 = "hel" + "lo";
        System.out.println("s2 equals s3:" + s2.equals(s3));// 字符串常量共享，+ 或 subString() 结果并不共享

        System.out.println("STRING_ONE equals STRING_TWO:" + STRING_ONE.equals(STRING_TWO));

        String s4 = "greeting";
        int cpCount = s4.codePointCount(0, s4.length());
        System.out.println("cpCount:" + cpCount);
        char first = s4.charAt(0);
        char last = s4.charAt(s4.length() - 1);
        System.out.println("first :" + first + "; last: " + last);
    }
}
