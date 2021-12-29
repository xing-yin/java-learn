package javaCourse.concurrency.conc2.lock.deadlock;

/**
 * @author Alan Yin
 * @date 2021/11/5
 */

public class ThreadB extends Thread {

    private Count3 count3;

    public ThreadB(Count3 count3) {
        this.count3 = count3;
    }

    @Override
    public void run() {
        count3.lockMethod();
    }
}
