package javaCourse.concurrency.conc1.sync;

/**
 * @author Alan Yin
 * @date 2021/10/8
 */

public class TestSetGet {

    public static void main(String[] args) throws InterruptedException {
        final SetGet setGet = new SetGet();
        Thread t = new Thread(() -> {
            try {
                setGet.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        long start = System.currentTimeMillis();
        setGet.set(66);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
    }

    public static class SetGet {
        int a = 0;

        public synchronized void set(int v) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " setting " + v);
            Thread.sleep(1000);
            a = v;
            System.out.println(Thread.currentThread().getName() + " set " + v);
        }

        public synchronized int get() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " getting ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }
    }
}
