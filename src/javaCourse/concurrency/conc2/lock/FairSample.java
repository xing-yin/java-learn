package javaCourse.concurrency.conc2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示：公平锁和非公平锁
 * <p>
 * 在无需保证公平的情况下性能要好非常多
 *
 * @author Alan Yin
 * @date 2021/11/5
 */

public class FairSample {

    /**
     * 改成 false 性能会好 100 倍
     */
    private static ReentrantLock lock = new ReentrantLock(false);

    public static volatile int race = 0;

    public static final int THREAD_COUNT = 20;

    public static void increase() {
        lock.lock();
        // 变量自增操作·
        try {
            race++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        // 等待所有累加线程都结束
        while (Thread.activeCount() > count) {
            Thread.yield();
        }
        // fair=true ===> java.util.concurrent.locks.ReentrantLock,ts=9845
        // fair=false ===> java.util.concurrent.locks.ReentrantLock,ts=157
        System.out.println(String.format("%s,ts=%d", lock.getClass().getName(), (System.currentTimeMillis() - now)));
    }

}
