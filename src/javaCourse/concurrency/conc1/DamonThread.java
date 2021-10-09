package javaCourse.concurrency.conc1;

/**
 * 演示：守护线程
 *
 * @author Alan Yin
 * @date 2021/9/29
 */

public class DamonThread {

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程为:" + t.getName());
        };

        Thread thread = new Thread(task);
        thread.setName("my-thread");
        // daemon 为 true 时为守护线程，不会打印"当前线程为:"
        thread.setDaemon(true);
        thread.start();
        System.out.println("运行结束");
    }
}
