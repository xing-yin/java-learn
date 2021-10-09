package javaCourse.concurrency.conc1.operation;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class WaitAndNotify {

    public static void main(String[] args) {
        ProductAndCustomer pac = new ProductAndCustomer();
        Thread productThread = new Thread(() -> {
            try {
                pac.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"productThread");

        Thread customerThread = new Thread(() -> {
            try {
                pac.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"customerThread");

        productThread.start();
        customerThread.start();
    }
}
