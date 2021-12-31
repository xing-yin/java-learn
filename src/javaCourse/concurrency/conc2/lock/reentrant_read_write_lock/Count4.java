package javaCourse.concurrency.conc2.lock.reentrant_read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示：读写锁的使用
 *
 * @author Alan Yin
 * @date 2021/11/5
 */

public class Count4 {

    private final Map<String, Object> cache = new HashMap<>();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object readWrite(String key) {
        Object value = null;
        System.out.println("1.开启读锁获取缓存数据");
        readWriteLock.readLock().lock();
        try {
            value = cache.get(key);
            if (value == null) {
                System.out.println("2.数据不存在，释放读锁，开启写锁");
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    value = "custom value";
                } finally {
                    System.out.println("3.释放写锁");
                    readWriteLock.writeLock().unlock();
                }
                System.out.println("4.开启读锁");
                readWriteLock.readLock().lock();
            }
        } finally {
            System.out.println("5.释放读锁");
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    /**
     * 测试未释放读锁时获取写锁
     */
    public void testGetWriteLockBeforeReleaseReadLock() {
        readWriteLock.readLock().lock();
        try {
            System.out.println("读锁获取成功");
            System.out.println("未释放读锁，尝试获取写锁，begin");
            // 读锁与写锁是互斥的，这里会阻塞住
            readWriteLock.writeLock().lock();
            System.out.println("未释放读锁，尝试获取写锁，end");
        } finally {
            System.out.println("读锁释放成功");
            readWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        Count4 count4 = new Count4();
//        count4.readWrite("hello concurrency");
        count4.testGetWriteLockBeforeReleaseReadLock();
    }

}
