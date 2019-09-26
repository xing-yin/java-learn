package effectivejava.chapter9.item78;

/**
 * 正例:使用 volatile
 * 虽然 volatile 修饰符不执行互斥访问，但它可以保证任何一个线程在读取该字段的时候都将看到最近被写入的值
 *
 * @author yinxing
 * @date 2019/9/26
 */

import java.util.concurrent.TimeUnit;

/**
 * Cooperative thread termination with a volatile field
 * 具有可变字段的协同线程终止
 */
public class StopThread12 {

    private static volatile Boolean stopRequested = false;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
