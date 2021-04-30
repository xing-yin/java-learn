package topic.hook.shutdown_hook;

import java.util.concurrent.TimeUnit;

/**
 * 3.终端使用Ctrl+C触发的中断
 * <p>
 * 在命令行中编译：javac com/hook/HookTest3.java
 * 在命令行中运行：java com.hook.HookTest3 （之后按下Ctrl+C）
 *
 * @author Alan Yin
 * @date 2021/4/25
 */

public class HookTest3 {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook.....");
            }
        }));
    }

    public static void main(String[] args) {
        new HookTest3().start();
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
