package CSNote.concurrent.thread_use;

/**
 * 继承 Thread 类
 * @author yinxing
 * @date 2019/2/19
 */

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("This is MyThread!");
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        /**
         * 当调用 start() 方法启动一个线程时，虚拟机会将该线程放入就绪队列中等待被调度，当一个线程被调度时会执行该线程的 run() 方法。
         */
        mt.start();
    }
}
