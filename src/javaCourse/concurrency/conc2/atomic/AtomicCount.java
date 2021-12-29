package javaCourse.concurrency.conc2.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示：基于原子类的线程安全计数
 *
 * @author Alan Yin
 * @date 2021/12/1
 */

public class AtomicCount {

    private AtomicInteger count = new AtomicInteger(0);

    public int add() {
        return count.incrementAndGet();
    }

    public int getNum() {
        return count.get();
    }

}
