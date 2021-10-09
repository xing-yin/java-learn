package javaCourse.concurrency.conc1.operation;

/**
 * @author Alan Yin
 * @date 2021/9/29
 */

public class MyThread extends Thread {

    private String name;

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // 使用 object/this 的效果不同
        synchronized (object) {
            for (int i = 0; i < 100; i++) {
                System.out.println("=====" + name + ":" + i);
            }
        }
    }
}
