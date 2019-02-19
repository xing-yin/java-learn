package CSNote.concurrent.thread_use;

/**
 * 实现 Runnable 接口
 * @author yinxing
 * @date 2019/2/18
 */

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("This is MyRunnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
