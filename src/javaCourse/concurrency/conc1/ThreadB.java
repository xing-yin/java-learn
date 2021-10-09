package javaCourse.concurrency.conc1;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class ThreadB extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程 B");

        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();

        System.out.println("线程名称为：" + threadName);
        System.out.println("返回当前线程" + threadName + "的线程组中活动线程的数量：" + currentThread.getThreadGroup().activeCount());
        System.out.println("返回当前线程" + threadName + "的标识符：" + currentThread.getId());
        System.out.println("返回当前线程" + threadName + "的优先级：" + currentThread.getPriority());
        System.out.println("返回当前线程" + threadName + "的状态：" + currentThread.getState());
        System.out.println("返回当前线程" + threadName + "所属的线程：" + currentThread.getThreadGroup());
        System.out.println("当前线程" + threadName + "是否处于活跃状态：" + currentThread.isAlive());
        System.out.println("当前线程" + threadName + "是否为守护线程：" + currentThread.isDaemon());

    }

}
