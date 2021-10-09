package javaCourse.concurrency.conc1.sync;

/**
 * 演示：synchronized 对象锁
 * <p>
 * （对整个类生效：m1/m2 相同时间只能一个方法获取到锁）
 *
 * @author Alan Yin
 * @date 2021/10/8
 */

public class Thread3 {

    class InnerClass {
        private void m1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + ": InnerClass.m1:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }

        private void m2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + ":  InnerClass.m2:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void m1(InnerClass innerClass) {
        // 使用对象锁
        synchronized (innerClass) {
            innerClass.m1();
        }
    }

    public void m2(InnerClass innerClass) {
//        synchronized (innerClass) {
        innerClass.m2();
//        }
    }

    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
        InnerClass innerClass = thread3.new InnerClass();
        Thread t1 = new Thread(() -> {
            thread3.m1(innerClass);
        }, "t1");
        Thread t2 = new Thread(() -> {
            thread3.m2(innerClass);
        }, "t2");
        t1.start();
        t2.start();
    }

}
