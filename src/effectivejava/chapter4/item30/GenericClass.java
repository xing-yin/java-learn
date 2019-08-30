package effectivejava.chapter4.item30;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class GenericClass {

    /**
     * 反例: Uses raw types - unacceptable!
     * 使用原始类型-不可接受
     */
    public static Set union1(Set s1, Set s2) {
        // warning: [unchecked] unchecked call
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    /**
     * 正例: Generic method (泛型方法)
     * 此方法编译时不会生成任何警告，并提供类型安全性和易用性
     */
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        Set<String> guys = new HashSet<>(Arrays.asList("Tom", "Dick", "Harry"));
        Set<String> stooges = new HashSet<>(Arrays.asList("Larry", "Moe", "Curly"));
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }

}
