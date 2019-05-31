package CSNote.concurrent.thread_unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yinxing
 * @date 2019/3/1
 */

public class AtomicExample {

    private AtomicInteger cnt = new AtomicInteger();

    public void add() {
        cnt.incrementAndGet();
    }

    public int get() {
        return cnt.get();
    }

    /**
     * AtomicInteger 能保证多个线程修改的原子性。
     * 使用 AtomicInteger 重写之前线程不安全的代码之后得到以下线程安全实现：
     */
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 10000;
        AtomicExample example = new AtomicExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

}
