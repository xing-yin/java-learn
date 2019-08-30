package effectivejava.chapter4.item31;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class GenericClass {

    /**
     * 正例: 两个参数 s1 和 s2 都是 E 的生产者，所以 PECS 助记符告诉我们该声明应该如下：
     */
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> integerSet = new HashSet<>(Arrays.asList(1, 3, 5));
        Set<Double> doubleSet = new HashSet<>(Arrays.asList(2.0, 3.0, 6.0));
        Set<Number> numberSet = union(integerSet, doubleSet);
        System.out.println(numberSet);
    }

}
