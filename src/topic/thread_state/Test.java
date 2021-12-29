package topic.thread_state;

/**
 * @author Alan Yin
 * @date 2021/11/16
 */

public class Test implements Runnable {

    public static Thread thread1;

    public static Test obj;

    public static void main(String[] args) {
        obj = new Test();
        thread1 = new Thread(obj);

        // 创建 thread1,现在是初始状态（New）
        System.out.println("State of thread1 after creating it - " + thread1.getState());
        thread1.start();

        // thread1 - 就绪状态
        System.out.println("State of thread1 after calling start() method on it - " + thread1.getState());
    }

    @Override
    public void run() {
        MyThread myThread = new MyThread();
        Thread thread2 = new Thread(myThread);

        // 创建 thread2,现在是初始状态（New）
        System.out.println("State of thread2 after creating it - " + thread2.getState());
        thread2.start();

        // thread2 - 就绪状态
        System.out.println("State of thread2 after calling start() method on it - " + thread2.getState());

        // moving thread1 to timed waiting state
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 after calling sleep() method on it - " + thread2.getState());

        try {
            // 等待 thread2 终止
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 when it has finished it's execution - " + thread2.getState());

    }
}
