package topic.byte_test;

/**
 * Java 按字节计算字符串的长度
 *
 * @author Alan Yin
 * @date 2021/1/11
 */

public class ByteCountDemo {

    private static final String ENCODING = "UTF-8";

    public static int getStrByteLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        try {
            return s.getBytes(ENCODING).length;
        } catch (Exception e) {
            throw new IllegalStateException("获取字符串字节长度失败");
        }
    }

    public static int getStrLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 0, j = str.length(); i < j; i++) {
            //UTF-8编码格式中文占三个字节，GBK编码格式 中文占两个字节 ;
            len += (str.charAt(i) > 255 ? 3 : 1);
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(getStrByteLength("test"));
        System.out.println(getStrLength("test"));
    }

}
