package javacore;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @author yinxing
 * @date 2019/6/3
 */

public class Lecture7 {

    class Counter {
        private final AtomicLong counter = new AtomicLong();

        public void increase() {
            counter.incrementAndGet();
        }
    }

    // 利用原始数据类型的效果和 AtomicLong 一样
    class CompactCounter {
        private volatile long counter;

        private final AtomicLongFieldUpdater<CompactCounter> UPDATER = AtomicLongFieldUpdater.newUpdater(CompactCounter.class, "counter");

        public void increase() {
            UPDATER.incrementAndGet(this);
        }
    }
}
