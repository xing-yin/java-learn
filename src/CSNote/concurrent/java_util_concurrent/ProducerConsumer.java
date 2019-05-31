package CSNote.concurrent.java_util_concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yinxing
 * @date 2019/2/27
 */

public class ProducerConsumer {

    /**
     * java.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现：
     * <p>
     * FIFO 队列 ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
     * 优先级队列 ：PriorityBlockingQueue
     * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，直到队列有空闲位置。
     */

    /**
     * 示例1:使用 BlockingQueue 实现生产者消费者问题
     */
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("producing.......");
        }
    }

    private static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consuming.......");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
        }

        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }

        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }

    }

}
