package core.chap3;

import java.math.BigInteger;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class BigIntegerTest {

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger(String.valueOf(1));
        System.out.println(bigInteger.add(BigInteger.valueOf(2)).intValue());
    }
}
