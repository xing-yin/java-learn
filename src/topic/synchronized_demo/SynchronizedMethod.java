package topic.synchronized_demo;

/**
 * 演示：同步代码块
 *
 * @author Alan Yin
 * @date 2021/12/14
 */

public class SynchronizedMethod {

    public int sum;

    public synchronized void syncMethod() {
        sum++;
    }
}
