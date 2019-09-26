package effectivejava.chapter9.item79;

import effectivejava.chapter3.item18.ForwardingSet;

import java.util.*;

/**
 * @author yinxing
 * @date 2019/9/26
 */

public class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();

    /**
     * 观察者通过调用 addObserver 方法预订通知
     *
     * @param observer
     */
    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * 观察者通过调用 removeObserver 方法取消预订
     *
     * @param observer
     * @return
     */
    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    /**
     * 反例: 在同步代码块中调用外部方法，造成死锁、性能降低、不确定的行为
     * Broken - invokes alien method from synchronized block!
     *
     * @param elemnent
     */
    public void notifyElementAdded0(E elemnent) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, elemnent);
            }
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
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            // 给 observers 列表拍张“快照”，然后没有锁也可以安全地遍历这个列表
            snapshot = new ArrayList<>(observers);
        }
        for (SetObserver<E> observer : snapshot) {
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

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
        set.addObserver((s, e) -> System.out.println(e));
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
