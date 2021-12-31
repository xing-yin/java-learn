package javaCourse.concurrency.conc2.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示：使用 Lock 实现同步，解决线程安全问题
 *
 * @author Alan Yin
 * @date 2021/12/1
 */

public class SyncCount {

    private int num;

    private Lock lock = new ReentrantLock();

    public int add() {
        lock.lock();
        try {
            num++;
            return num;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }

}
