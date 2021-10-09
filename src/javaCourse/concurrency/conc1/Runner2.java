package javaCourse.concurrency.conc1;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class Runner2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入 Runner2 运行");
        }

        boolean result1 = Thread.currentThread().isInterrupted();
        // 重置状态
        boolean result2 = Thread.interrupted();
        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("Runner2.result1 is: " + result1);
        System.out.println("Runner2.result2 is: " + result2);
        System.out.println("Runner2.result3 is: " + result3);
    }
}
