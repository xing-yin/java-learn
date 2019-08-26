package effectivejava.chapter3.item18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * 反例: 使用继承，代码比较脆弱
 * <p>
 * 与方法调用不同，继承打破了封装。（一个子类依赖于其父类的实现细节来保证其正确的功能。 父类的实现可能会从发布版本不断变化）
 * 问题在于重写，但是即使不重写现有方法，一旦父类新增的新方法和子类现在定义的方法签名相同但返回类型不同，会编译失败。
 *
 * @author yinxing
 * @date 2019/8/22
 */

/**
 * 为了具体说明，假设有一个使用 HashSet 的程序。 为了调整程序的性能，需要查询 HashSet ，
 * 从创建它之后已经添加了多少个元素（不要和当前的元素数量混淆，当元素被删除时数量也会下降）。
 * 为了提供这个功能，编写了一个 HashSet 变体，它保留了尝试元素插入的数量，并导出了这个插入数量的一个访问方法。
 * HashSet 类包含两个添加元素的方法，分别是 add 和 addAll，所以我们重写这两个方法：
 */
public class InstrumentedHashSet1<E> extends HashSet<E> {

    /**
     * the number of attempted element insertions
     */
    private int addCount = 0;

    public InstrumentedHashSet1() {
    }

    public InstrumentedHashSet1(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
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
        /**
         * 上面的代码不能按照预期工作: 因为在 HashSet 内部，addAll 方法是基于它的 add 方法来实现的，每个元素被计算了两次
         */
        InstrumentedHashSet1<String> instrumentedHashSet = new InstrumentedHashSet1<>();
        instrumentedHashSet.addAll(Arrays.asList("a", "b", "c"));
        // 预期是3，实际是6
        System.out.println("addCount :" + instrumentedHashSet.getAddCount());
    }
}
