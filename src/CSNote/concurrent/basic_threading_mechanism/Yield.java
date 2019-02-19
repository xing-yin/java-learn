package CSNote.concurrent.basic_threading_mechanism;

/**
 * @author yinxing
 * @date 2019/2/19
 */

public class Yield implements Runnable {

    /**
     * 对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，
     * 可以切换给其它线程来执行。该方法只是对线程调度器的一个**建议**，而且也只是建议具有相同优先级的其它线程可以运行。
     */
    @Override
    public void run() {
        System.out.println("start");
        Thread.yield();
        System.out.println("end");
    }

    public static void main(String[] args) {
        Yield yield = new Yield();
        yield.run();
        for (int i = 0; i < 5; i++) {
            System.out.println("i=" + i);
        }
    }
}
