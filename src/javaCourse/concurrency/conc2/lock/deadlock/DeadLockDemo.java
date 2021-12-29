package javaCourse.concurrency.conc2.lock.deadlock;

/**
 * @author Alan Yin
 * @date 2021/11/5
 */

public class DeadLockDemo {

    public static void main(String[] args) {
        Count3 count3 = new Count3();
        ThreadA threadA = new ThreadA(count3);
        threadA.setName("线程 A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("线程 B");
        threadB.start();
    }
}
