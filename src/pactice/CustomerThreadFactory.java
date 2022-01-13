package pactice;

import java.util.concurrent.*;

/**
 * 演示：自定义 ThreadFactory
 *
 * @author: Alan Yin
 * @date: 2021/12/8
 */
public class CustomerThreadFactory implements ThreadFactory {

    private String namePrefix;

    private Boolean daemon;

    public CustomerThreadFactory(String namePrefix, Boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix);
        thread.setDaemon(daemon);
        return thread;
    }


    public static ThreadPoolExecutor initThreadPoolExecutor() {
        int coreSize = Runtime.getRuntime().availableProcessors();
        int maxSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(500);
        CustomerThreadFactory threadFactory = new CustomerThreadFactory("myThread-", true);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,
                maxSize,
                1, TimeUnit.MINUTES,
                workQueue,
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
