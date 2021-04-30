package topic.hook.event_callback;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 调用者
 *
 * @author Alan Yin
 * @date 2021/4/25
 */

public class Caller {

    public Caller() {
        this.queue = new ArrayBlockingQueue<Event>(10);
        // 真实系统中肯定是在运行中忘该队列中放置事件
        this.queue.add(new Event("click22222"));
    }

    private BlockingQueue<Event> queue;

    public void setQueue(BlockingQueue<Event> queue) {
        this.queue = queue;
    }

    public void call(Callback callback) throws InterruptedException {
        System.out.println("before");
        Event event = queue.take();
        if ("click".equals(event.getEventName())) {
            callback.doWhenEventHappen();
        }
        System.out.println("after");
    }
}
