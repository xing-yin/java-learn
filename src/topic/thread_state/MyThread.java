package topic.thread_state;

/**
 * 演示：线程的状态
 *
 * @author Alan Yin
 * @date 2021/11/16
 */

public class MyThread implements Runnable {

    @Override
    public void run() {
        // 模拟超时等待
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State of MyThread while it called join() mothod on MyThread -" + Test.thread1.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
