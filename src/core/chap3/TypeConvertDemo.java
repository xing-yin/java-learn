package core.chap3;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class TypeConvertDemo {

    public static void main(String[] args) {
        Long b = 111111111111111111L;
//        Integer c = Math.toIntExact(b); // java.lang.ArithmeticException: integer overflow

        double d = 9.24;
        int e = (int) d;
        System.out.println("e: " + e);

        double f = 9.54;
        int g = (int) Math.round(f);
        System.out.println("g: " + g);

        System.out.println("强转完全不同的值,byte(300):" + (byte) 300);
    }
}
