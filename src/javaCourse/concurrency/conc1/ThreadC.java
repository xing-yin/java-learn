package javaCourse.concurrency.conc1;

import java.util.concurrent.Callable;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class ThreadC implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        System.out.println("线程C");
        return "线程C";
    }
}
