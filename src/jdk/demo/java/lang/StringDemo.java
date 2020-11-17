package jdk.demo.java.lang;

import java.io.UnsupportedEncodingException;

/**
 * @author Alan Yin
 * @date 2019/12/24
 */

public class StringDemo {

    private void constructorTest() {
        String s1 = new String();

        String s2 = new String("abc");

        String s3 = "abc";

        char[] chars = new char[]{'a', 'b', 'c'};
        // 使用 java.util 包中的 Arrays 类复制(底层真正使用 System.arraycopy，intrnisic 优化过)
        String s4 = new String(chars);

        byte[] bytes = new byte[]{'a', 'b', 'c'};
        try {
            String s5 = new String(bytes, 0, 2, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void systemCopyTest() {
        char[] chars1 = new char[]{'a', 'b', 'c'};
        char[] chars2 = new char[4];
        System.arraycopy(chars1, 0, chars2, 0, 2);
        System.out.println(chars2);
    }

    private static void hashcodeTest() {
        String s1 = new String();
        // 测试空字符串哈希值为 0
        System.out.println(s1.hashCode() == 0); // true
        String s2 = "abc";
        System.out.println(s2.hashCode());
    }

    private static void compareToTest() {
        String s1 = "abc";
        String s2 = "Abc";
//        System.out.println('A'+0); // 65
//        System.out.println('a'+0); // 97
        System.out.println(s1.compareTo(s2));

        String s3 = "abcd";
        System.out.println(s1.compareTo(s3));
    }

    private static void startWithTest() {
        String s = "abc123";
        System.out.println(s.startsWith("c", 2));
    }

    private static void trimTest() {
        String s1 = " abc";
        String s2 = "abc ";
        String s3 = " a bc ";
        String s4 = " a bc ".replaceAll("\\s+", "");
        System.out.println(s1.trim());
        System.out.println(s2.trim());
        System.out.println(s3.trim());
        System.out.println(s4);
    }

    private static void internTest() {
        String s1 = " abc";
        String s2 = s1.intern();
        System.out.println(s1.equals(s2));
    }


    public static void main(String[] args) {
//        systemCopyTest();

//        hashcodeTest();

//        compareToTest();

//        startWithTest();

//        trimTest();

        internTest();
    }

}
