package effectivejava.chapter9.item81;


/**
 * wait 方法被用来使线程等待某个条件。
 * 它必须在同步区域内部被调用，这个同步区域将对象锁定在了调用 wait 方法的对象上。
 *
 * @author yinxing
 * @date 2019/9/26
 */

public class WaitDemo {

    private static boolean isSuccess;

    /**
     * 下面是使用 wait 方法的标准模式：
     * <p>
     * 始终应该使用 wait 循环模式来调用 wait 方法；永远不要在循环之外调用 wait 方法。循环会在等待之前和之后对条件进行测试。
     */
    public void waitMethod(Object obj) throws InterruptedException {
        synchronized (this) {
            while (!isSuccess) {
                obj.wait();
            }
        }
    }
}
