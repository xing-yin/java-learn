package pactice;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Alan Yin
 * @date: 2021/12/16
 */
public class ReadWriteLockCounter {

    private int sum = 0;

    // 可重入读写锁：公平锁演示、
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public int addAndGet() {
        try {
            // 写锁：独占锁；与读锁互斥
            lock.writeLock().lock();
            return sum++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getSum() {
        try {
            // 读锁：共享锁，保证可见性
            lock.readLock().lock();
            return sum;
        } finally {
            lock.readLock().unlock();
        }
    }
}
