package javacore.lecture7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 使用原始数据类型、数组甚至本地代码实现等，在性能极度敏感的场景往往具有比较大的优势，
 * 用其替换掉包装类、动态数组(如ArrayList)等可 以作为性能优化的备选项。
 * 一些追求极致性能的产品或者类库，会极力避免创建过多对象
 *
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

    /**
     * 利用原始数据类型的效果和 AtomicLong 一样
     */
    class CompactCounter {
        private volatile long counter;

        private final AtomicLongFieldUpdater<CompactCounter> UPDATER = AtomicLongFieldUpdater.newUpdater(CompactCounter.class, "counter");

        public void increase() {
            UPDATER.incrementAndGet(this);
        }
    }
}
