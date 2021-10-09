package javaCourse.concurrency.conc1.operation;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class ProductAndCustomer {

    /**
     * 定义生产最大量
     */
    private final int MAX_COUNT = 20;

    private int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::product run:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("仓库已满，不必再生产");
                wait();
            } else {
                productCount++;
            }
            notifyAll();
        }

    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::customer run:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("仓库已经清仓，无法消费");
                wait();
            } else {
                productCount--;
            }
            notifyAll();
        }
    }

}
