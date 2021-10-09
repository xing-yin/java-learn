package javaCourse.concurrency.conc1.operation;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class Join {

    public static void main(String[] args) {
        Object ob = new Object();

        MyThread myThread = new MyThread("myThread-");
        myThread.setObject(ob);
        myThread.start();

        synchronized (ob) {
            for (int i = 0; i < 100; i++) {
                try {
                    ob.wait();
                    myThread.join(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前的线程为:" + Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}
