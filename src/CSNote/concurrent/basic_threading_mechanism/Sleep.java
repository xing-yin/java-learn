package CSNote.concurrent.basic_threading_mechanism;

/**
 * @author yinxing
 * @date 2019/2/19
 */

public class Sleep implements Runnable {

    /**
     * Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。
     * <p>
     * sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，
     * 因此必须在本地进行处理。线程中抛出的其它异常也同样需要在本地进行处理。
     */
    @Override
    public void run() {
        try {
            System.out.println("start");
            Thread.sleep(3000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sleep sleep = new Sleep();
        sleep.run();
    }
}
