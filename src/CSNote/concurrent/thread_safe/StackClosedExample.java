package CSNote.concurrent.thread_safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 栈封闭
 *
 * @author yinxing
 * @date 2019/3/6
 */

public class StackClosedExample {

    public void sum100() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            cnt++;
        }
        System.out.println(cnt);
    }

    /**
     * 多个线程访问同一个方法的局部变量时，不会出现线程安全问题，因为局部变量存储在虚拟机栈中，属于线程私有的。
     *
     * @param args
     */
    public static void main(String[] args) {
        StackClosedExample example = new StackClosedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example.sum100());
        executorService.execute(() -> example.sum100());
        executorService.shutdown();
    }
}
