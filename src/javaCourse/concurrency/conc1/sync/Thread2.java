package javaCourse.concurrency.conc1.sync;

/**
 * 演示：synchronized 类锁
 * <p>
 * （对整个类生效：m1/m2 相同时间只能一个方法获取到锁）
 *
 * @author Alan Yin
 * @date 2021/10/8
 */

public class Thread2 {

    public void m1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void m2() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final Thread2 thread2 = new Thread2();
        Thread t1 = new Thread(() -> {
            thread2.m1();
        }, "t1");
        Thread t2 = new Thread(() -> {
            thread2.m2();
        }, "t2");
        t1.start();
        t2.start();
    }
}
