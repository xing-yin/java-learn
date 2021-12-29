package javaCourse.concurrency.conc2.atomic;

/**
 * @author Alan Yin
 * @date 2021/12/1
 */

public class CountDemo {

    public static void main(String[] args) {
        // 1.线程不安全:由于线程安全问题，最终结果有可能是错误的
//        ThreadUnSafeCount count = new ThreadUnSafeCount();
        // 2.线程安全：基于 Lock
//        SyncCount count = new SyncCount();
        // 3.线程安全：基于 AtomicXXX
//        AtomicCount count = new AtomicCount();
        // 4.线程安全：基于 LongAdder
        LongAdderCount count = new LongAdderCount();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    count.add();
                }
            }).start();
        }
        System.out.println("num is:" + count.getNum());
    }
}
