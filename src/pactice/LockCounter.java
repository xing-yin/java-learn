package pactice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 演示：Lock 示例
 *
 * @author: Alan Yin
 * @date: 2021/12/16
 */
public class LockCounter {

    private int sum = 0;

    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return sum++;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        int loopNum = 100_000;
        LockCounter counter = new LockCounter();
        IntStream.range(0, loopNum).parallel()
                .forEach(i -> counter.addAndGet());
        System.out.println(counter.getSum());
    }

}
