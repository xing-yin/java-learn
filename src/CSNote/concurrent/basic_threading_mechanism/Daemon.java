package CSNote.concurrent.basic_threading_mechanism;

import CSNote.concurrent.thread_use.MyRunnable;

/**
 * @author yinxing
 * @date 2019/2/19
 */

public class Daemon {

    /**
     * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
     * <p>
     * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
     * <p>
     * main() 属于非守护线程。
     * <p>
     * 使用 setDaemon() 方法将一个线程设置为守护线程
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.setDaemon(true);
    }
}
