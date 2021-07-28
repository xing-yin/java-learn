package pactice;

/**
 * @author Alan Yin
 * @date 2021/6/23
 */

public class NorDemo {

    public static void main(String[] args) {
        int res = 21;
        System.out.println(getBit(res, 0));
        System.out.println(getBit(res, 1));
        System.out.println(getBit(res, 2));
        System.out.println(getBit(res, 3));
        System.out.println(getBit(res, 4));
    }

    private static boolean getBit(int num, int i) {
        return ((num & (1 << i)) != 0);
    }

}
