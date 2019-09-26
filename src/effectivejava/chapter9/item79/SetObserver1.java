package effectivejava.chapter9.item79;

/**
 * @author yinxing
 * @date 2019/9/26
 */
@FunctionalInterface
public interface SetObserver1<E> {

    /**
     * invoked when an element is added to the observable set
     */
    void added(ObservableSet1<E> set, E element);

}
