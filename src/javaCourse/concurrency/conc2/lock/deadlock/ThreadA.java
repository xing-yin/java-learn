package javaCourse.concurrency.conc2.lock.deadlock;

/**
 * @author Alan Yin
 * @date 2021/11/5
 */

public class ThreadA extends Thread {

    private Count3 count3;

    public ThreadA(Count3 count3) {
        this.count3 = count3;
    }

    @Override
    public void run() {
        count3.add();
    }
}
