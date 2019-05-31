package CSNote.concurrent.basic_threading_mechanism;

/**
 * @author yinxing
 * @date 2019/2/19
 */

public class InterruptExample {

    /**
     * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，
     * 那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
     * <p>
     * 对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，
     * 因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。
     */
    private static class MyThread1 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
     * 因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
     */
    private static class MyThread2 extends Thread {

        @Override
        public void run() {
            while (!interrupted()) {
                System.out.println("running ");
            }
            System.out.println("Thread end");
        }
    }

    public static void main(String[] args) {
//        /**
//         * 示例1
//         */
//        Thread thread1 = new MyThread1();
//        thread1.start();
//        thread1.interrupt();
//        System.out.println("Main run");

        /**
         * 示例2
         */
        Thread thread2 = new MyThread2();
        thread2.start();
        thread2.interrupt();
    }

}
