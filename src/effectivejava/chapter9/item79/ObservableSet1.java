package effectivejava.chapter9.item79;

import effectivejava.chapter3.item18.ForwardingSet;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 正例: 使用 CopyOnWriteArrayList
 * <p>
 * 要将外来方法的调用移出同步的代码块，还有一种更好的方法。
 * Java 类库提供了一个并发集合（ concurrent collection ），详见第 81 条，称作 CopyOnWriteArrayList，这是专门为此定制的。
 * 这个 CopyOnWriteArrayList 是 ArrayList 的一种变体，它通过重新拷贝整个底层数组，在这里实现所有的写操作。
 * 由于内部数组永远不改动，因此迭代不需要锁定，速度也非常快。
 * 如果大量改动， CopyOnWriteArrayList 的性能将大受影响，如果改动很少或不改动，这个很适合。
 *
 * @author yinxing
 * @date 2019/9/26
 */

public class ObservableSet1<E> extends ForwardingSet<E> {

    public ObservableSet1(Set<E> set) {
        super(set);
    }

    private final List<SetObserver1<E>> observers = new CopyOnWriteArrayList<>();

    /**
     * 注意使用了 CopyOnWriteArrayList，不再需要显示地 synchronized 同步
     *
     * @param observer
     */
    public void addObserver(SetObserver1<E> observer) {
        observers.add(observer);
    }

    /**
     * 观察者通过调用 removeObserver 方法取消预订
     *
     * @param observer
     * @return
     */
    public boolean removeObserver(SetObserver1<E> observer) {
        return observers.remove(observer);
    }

    /**
     * 反例: 在同步代码块中调用外部方法，造成死锁、性能降低、不确定的行为
     * Broken - invokes alien method from synchronized block!
     *
     * @param elemnent
     */
    public void notifyElementAdded0(E elemnent) {
        for (SetObserver1<E> observer : observers) {
            observer.added(this, elemnent);
        }
    }

    /**
     * 正例: 将外部方法调用移到同步代码块外部
     * 如使用快照
     * Alien method moved outside of synchronized block - open calls
     *
     * @param elemnent
     */
    public void notifyElementAdded(E elemnent) {
        for (SetObserver1<E> observer : observers) {
            observer.added(this, elemnent);
        }
    }

    @Override
    public boolean add(E e) {
        Boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Boolean result = false;
        for (E element : c) {
            result |= add(element);
        }
        // Calls notifyElementAdded
        return result;
    }

}
