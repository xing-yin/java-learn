package javaCourse.concurrency.conc1;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class ThreadA extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程 A");
    }
}
