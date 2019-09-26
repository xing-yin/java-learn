package effectivejava.chapter9.item78;

/**
 * 正例: 除非读和写操作都被同步，否则无法保证同步能起作用
 *
 *
 *
 * @author yinxing
 * @date 2019/9/26
 */

import java.util.concurrent.TimeUnit;

/**
 * 该类中被同步方法即使没有同步(synchronized)也是原子的。
 * 换句话说，这些方法的同步只是为了它的通信效果，而不是为了互斥访问。
 */
public class StopThread11 {

    private static Boolean stopRequested = false;

    private static synchronized Boolean stopRequested() {
        return stopRequested;
    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    /**
     *
     */
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested()) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

}
