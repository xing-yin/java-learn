package javaCourse.concurrency.conc1;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class RunnerDemo {

    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1);
        thread1.setName("my-thread1");

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2);
        thread2.setName("my-thread2");

        thread1.start();
        thread2.start();

        thread2.interrupt();
        System.out.println(Thread.activeCount());

        Thread.currentThread().getThreadGroup().list();
        System.out.println("========"+ Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        Thread.currentThread().getThreadGroup().getParent().list();

    }
}
