package pactice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: Alan Yin
 * @date: 2021/12/8
 */
public class RandomSleepTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Integer sleep = new Random().nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

}
