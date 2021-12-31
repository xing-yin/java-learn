package javaCourse.concurrency.conc2.atomic;

/**
 * 演示：线程不安全计数
 *
 * @author Alan Yin
 * @date 2021/11/8
 */

public class ThreadUnSafeCount {

    private int num = 0;

    public int add() {
        return ++num;
    }

    public int getNum() {
        return num;
    }

}
