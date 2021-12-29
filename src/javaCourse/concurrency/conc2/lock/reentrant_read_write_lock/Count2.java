package javaCourse.concurrency.conc2.lock.reentrant_read_write_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示：使用 ReentrantReadWriteLock
 *
 * @author Alan Yin
 * @date 2021/11/5
 */

public class Count2 {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put() {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
