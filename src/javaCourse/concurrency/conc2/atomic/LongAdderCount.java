package javaCourse.concurrency.conc2.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 演示：LongAdder
 *
 * @author Alan Yin
 * @date 2021/12/2
 */

public class LongAdderCount {

    private LongAdder count = new LongAdder();

    public void add() {
        count.increment();
    }

    public long getNum() {
        return count.sum();
    }

}
