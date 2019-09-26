package effectivejava.chapter9.item79;

/**
 * @author yinxing
 * @date 2019/9/26
 */
@FunctionalInterface
public interface SetObserver<E> {

    /**
     * invoked when an element is added to the observable set
     */
    void added(ObservableSet<E> set,E element);

}
