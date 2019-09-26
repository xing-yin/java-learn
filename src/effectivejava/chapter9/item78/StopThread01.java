package effectivejava.chapter9.item78;

/**
 * 反例:没有同步造成的 虚拟机提升导致的活性失败
 *
 * @author yinxing
 * @date 2019/9/26
 */

import java.util.concurrent.TimeUnit;

/**
 * Broken! - How long would you expect this program to run?
 */
public class StopThread01 {

    private static Boolean stopRequested = false;

    /**
     * 该程序永远不会终止：因为后台线程永远在循环！不会预期 1秒钟左右结束
     */
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

    /**
     * 问题分析：由于没有同步，就不能保证后台线程何时‘看到’主线程对 stopRequested 的值所做的改变。
     * <p>
     * 没有同步，虚拟机将以下代码:
     * <p>
     * while (!stopRequested) {
     * i++;
     * }
     * <p>
     * 转变为:[称作为 提升]  这是 OpenJDK Server VM 的工作
     * 结果是一个活性失败 （liveness failure）：这个程序并没有得到提升。
     * if (!stopRequested) {
     * while(true)
     * }
     */

}
