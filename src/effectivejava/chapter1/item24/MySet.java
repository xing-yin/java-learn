package effectivejava.chapter1.item24;

import java.util.AbstractSet;
import java.util.Iterator;

/**
 * @author yinxing
 * @date 2019/8/26
 */

/**
 * Typical use of a nonstatic member class
 * 非静态类的典型应用
 */
public class MySet<E> extends AbstractSet<E> {

    @Override
    public Iterator<E> iterator() {
        // 内部私有类
        return new MyIterator();
    }

    @Override
    public int size() {
        return 0;
    }

    private class MyIterator implements Iterator<E> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

}
