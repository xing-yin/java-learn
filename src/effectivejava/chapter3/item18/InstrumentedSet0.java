package effectivejava.chapter3.item18;

/**
 * InstrumentedSet 类被称为包装类，因为每个 InstrumentedSet 实例都包含（“包装”）另一个 Set 实例。
 * 这也被称为装饰器模式[Gamma95]，因为 InstrumentedSet 类通过添加计数功能来“装饰”一个集合。
 *
 * @author yinxing
 * @date 2019/8/22
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Wrapper class - uses composition in place of inheritance
 */
public class InstrumentedSet0<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedSet0(Set<E> set) {
        super(set);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        Set<String> set = new InstrumentedSet0<>(new TreeSet<>());
        Set<String> s = new InstrumentedSet0<>(new HashSet<>(16));
    }
}
