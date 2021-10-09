package javaCourse.concurrency.conc1.sync;

/**
 * 演示： synchronized 类锁
 * <p>
 * （对整个类生效：m1/m2 相同时间只能一个方法获取到锁）
 *
 * @author Alan Yin
 * @date 2021/10/8
 */

public class Thread1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread ta = new Thread(thread1, "A");
        Thread tb = new Thread(thread1, "B");
        ta.start();
        tb.start();
    }
}
