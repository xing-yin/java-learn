package jdk.demo.java.util.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 演示：Semaphore 信号量使用
 *
 * @author: Alan Yin
 * @date: 2022/1/4
 */
public class SemaphoreCounter {

    private int sum = 0;

    private Semaphore readSemaphore = new Semaphore(100, true);

    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            return ++sum;
        } finally {
            writeSemaphore.release();
        }
    }

    public int getSum() {
        try {
            readSemaphore.acquireUninterruptibly();
            return sum;
        } finally {
            readSemaphore.release();
        }
    }

}
