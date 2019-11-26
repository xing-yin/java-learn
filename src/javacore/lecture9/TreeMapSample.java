package javacore.lecture9;

/**
 * 对于TreeMap ，它的整体顺序是由键的顺序关系决定的，通过Comparator或Comparable(自然顺序)来决定。
 *
 * @author yinxing
 * @date 2019/11/26
 */

public class TreeMapSample<K, V> {


    /**
     * TreeMap 的 put 方法实现：
     * 类似hashCode和equals的约定，为了避免模棱两可的情况，
     * 自然顺序同样需要符合一个约定，就是compareTo 的返回值需要和equals一致，否则就会出现模棱两可情况。
     */
//    public V put(K key, V value) {
//        Entry<K,V> t = ...
//        cmp = k.compareTo(t.key);
//        if (cmp < 0)
//            t = t.left;
//        else if (cmp > 0)
//            t = t.right;
//        else
//            return t.setValue(value);
//        // ...
//    }

    /**
     * 构建一个具有优先级的调度系统的问题，其本质就是个典型的优先队列场景，
     * Java标准库提供了基于二叉堆实现的PriorityQueue，它们都是依赖于同一种排序机制，当然也包括TreeMap 的马甲TreeSet 。
     */
}
