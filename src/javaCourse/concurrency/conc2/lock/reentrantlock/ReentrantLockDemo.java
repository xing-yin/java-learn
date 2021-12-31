package javaCourse.concurrency.conc2.lock.reentrantlock;

/**
 * @author Alan Yin
 * @date 2021/11/5
 */

public class ReentrantLockDemo {

    public static void main(String[] args) {
        final Count1 count1 = new Count1();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> count1.get())
                    .start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> count1.put())
                    .start();
        }
    }
}
