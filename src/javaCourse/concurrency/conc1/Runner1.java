package javaCourse.concurrency.conc1;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class Runner1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入 Runner1 运行");
        }
    }
}
