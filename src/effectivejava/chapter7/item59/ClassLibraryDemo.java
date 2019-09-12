package effectivejava.chapter7.item59;

import java.util.Random;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class ClassLibraryDemo {

    /**
     * 反例:
     * 这个自定义的方法缺点:
     * a.如果 n 是小的平方数，随机数序列会在相当短的时间内重复。
     * b.如果 n 不是 2 的幂，那么平均而言，一些数字将比其他数字更频繁地返回。
     * c.在极少数情况下会返回超出指定范围的数字，这是灾难性的结果。
     */
    private static Random random = new Random();

    public static int randomNum(int n) {
        return Math.abs(random.nextInt()) % n;
    }

}
