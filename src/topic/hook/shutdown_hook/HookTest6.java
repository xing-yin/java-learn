package topic.hook.shutdown_hook;

import java.util.concurrent.TimeUnit;

/**
 * 6.使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）
 * <p>
 * jps 查看进程，然后用 kill pid 干掉进程
 *
 * @author Alan Yin
 * @date 2021/4/25
 */

public class HookTest6 {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook.....");
            }
        }));
    }

    public static void main(String[] args) {
        new HookTest6().start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("thread is running....");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
