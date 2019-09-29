package effectivejava.chapter9.item84;

/**
 * 反例: 糟糕的倒计时锁实现---忙等待不断
 * Awful CountDownLatch implementation - busy-waits incessantly!
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class SlowCountDownLatch {

    private int count;

    public SlowCountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(count + "<0");
        }
        this.count = count;
    }

    public void await() {
        while (true) {
            synchronized (this) {
                if (count == 0) {
                    return;
                }
            }
        }
    }

    public synchronized void countDown() {
        if (count != 0) {
            count--;
        }
    }
}
