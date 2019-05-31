package CSNote.concurrent.thread_unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yinxing
 * @date 2019/3/1
 */

public class ThreadUnsafeExample {

    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }

    /**
     * 如果多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的。
     * <p>
     * 以下代码演示了 10000 个线程同时对 cnt 执行自增操作，操作结束之后它的值有可能小于 10000。
     */
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 10000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
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
