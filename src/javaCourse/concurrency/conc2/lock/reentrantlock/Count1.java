package javaCourse.concurrency.conc2.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示：使用 ReentrantLock
 *
 * @author Alan Yin
 * @date 2021/11/5
 */

public class Count1 {

    private final ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void put() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 在使用阻塞等待获取锁的方式中，必须在try代码块之外，
     * 并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
     * <p>
     * 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
     * <p>
     * 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，
     * unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
     * <p>
     * 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。
     * java.concurrent.LockShouldWithTryFinallyRule.rule.desc
     */
    public void badLockMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            exceptionMethod();
            // 反例写法
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void exceptionMethod() {
        throw new IllegalStateException("状态异常");
    }

    public static void main(String[] args) {
        Count1 count1 = new Count1();
        count1.badLockMethod();
    }
}
