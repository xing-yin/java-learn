package core.chap3;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class ConstantDemo {

    private static final int CONSTANT_VARIABLE = 2;

    public static void main(String[] args) {
        final int a = 1;
        // error: a is define final
        // a=2;
        System.out.println(Math.PI);

    }
}
