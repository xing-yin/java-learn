package javaCourse.concurrency.conc1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class ThreadDemo {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("this is main thread");

        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("this is main thread");

        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("this is main thread");
        try {
            System.out.println("get result:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
