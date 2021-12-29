package javaCourse.concurrency.conc2.lock.lock_support;

import java.util.concurrent.locks.LockSupport;

/**
 * 演示：LockSupport
 * Spring 中就大量使用了 LockSupport
 *
 * @author Alan Yin
 * @date 2021/11/8
 */

public class LockSupportSample {

    public static Object lock = new Object();

    static ChangeObjectThread thread1 = new ChangeObjectThread("t1");

    static ChangeObjectThread thread2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupted");
                }
                System.out.println("continue execute");
            }
        }

        public static void main(String[] args) throws InterruptedException {
            thread1.start();
            Thread.sleep(1000L);
            thread2.start();
            Thread.sleep(2000L);
            thread1.interrupt();
            LockSupport.unpark(thread2);
            thread1.join();
            thread2.join();
        }
    }
}
