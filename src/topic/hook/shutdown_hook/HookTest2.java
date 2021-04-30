package topic.hook.shutdown_hook;

/**
 * @author Alan Yin
 * @date 2021/4/25
 */

public class HookTest2 {

    public static void main(String[] args) {
        new HookTest1().start();
        System.out.println("application is running");
        System.exit(-1);
    }

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute Hook.....");
            }
        }));
    }
}
