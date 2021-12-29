package javaCourse.concurrency.conc2.lock.reentrant_read_write_lock;

/**
 * @author Alan Yin
 * @date 2021/11/5
 */

public class Demo {

    public static void main(String[] args) {
        final Count2 count2 = new Count2();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> count2.get())
                    .start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> count2.put())
                    .start();
        }
    }

    /**
     * Thread-0 get begin
     * Thread-4 get begin
     * Thread-1 get begin
     * Thread-2 get begin
     * Thread-3 get begin
     * Thread-0 get end
     * Thread-1 get end
     * Thread-3 get end
     * Thread-2 get end
     * Thread-4 get end
     * Thread-6 put begin
     * Thread-6 put end
     * Thread-5 put begin
     * Thread-5 put end
     * Thread-7 put begin
     * Thread-7 put end
     * Thread-8 put begin
     * Thread-8 put end
     * Thread-9 put begin
     * Thread-9 put end
     *
     * 从打印结果可以看出：读锁不互斥，写锁互斥（读锁是共享锁，写锁是排他锁）
     */
}
