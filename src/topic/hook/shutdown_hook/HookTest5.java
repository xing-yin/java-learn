package topic.hook.shutdown_hook;

import java.util.concurrent.TimeUnit;

/**
 * 示例1：程序正常退出
 * <p>
 * 运行参数设置为：-Xmx20M 这样可以保证会有OutOfMemoryError的发生
 *
 * @author Alan Yin
 * @date 2021/4/25
 */

public class HookTest5 {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook.....");
            }
        }));
    }

    public static void main(String[] args) {
        new HookTest5().start();
        System.out.println("The Application is running");
        byte[] b = new byte[500 * 1024 * 1024];
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


/**
 * JDK提供了Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：
 * <p>
 * 1.程序正常退出
 * 2.使用System.exit()
 * 3.终端使用Ctrl+C触发的中断
 * 4.系统关闭
 * 5.OutOfMemory宕机
 * 6.使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）
 */

