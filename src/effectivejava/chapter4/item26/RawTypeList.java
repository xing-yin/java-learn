package effectivejava.chapter4.item26;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 反例：使用原始类型
 *
 * @author yinxing
 * @date 2019/8/27
 */

public class RawTypeList {

    /**
     * Fails at runtime - unsafeAdd method uses a raw type (List)!
     */
//    public static void main(String[] args) {
//        List<String> stringList = new ArrayList<>(4);
//        unsafeAdd(stringList, Integer.valueOf(42));
//        String s = stringList.get(0);
//    }

    private static void unsafeAdd(List<String> list, Object o) {
        /**
         *  warning: [unchecked] unchecked call to add(E) as a member of the raw type List
         */
//        list.add(o);
    }


    /**
     * 反例: Use of raw type for unknown element type - don't do this!
     * 对未知类型使用原始类型 - 不要这样做(可能会出现问题)
     *
     * @param s1
     * @param s2
     * @return
     */
    static int numElementsInCommon1(Set s1, Set s2) {
        int result = 0;
        for (Object o : s1) {
            if (s2.contains(o)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 正例:Uses unbounded wildcard type - typesafe and flexible
     * 使用无限制通配符，如 Set<E> 的无限制通配符 Set<?>
     */
    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o : s1) {
            if (s2.contains(o)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 无限制通配符 Set<?> 与原始类型 Set 之间有什么区别？ 问号真的给你放任何东西吗？
     * 这不是要点，但通配符类型是安全的，原始类型不是。 你可以将任何元素放入具有原始类型的集合中，轻易破坏集合的类型不变性;
     * 你不能把任何元素（除 null 之外）放入一个 Collection<?> 中。
     */

    public static void main(String[] args) {
        Set<String> s1 = new HashSet<>(2);
        s1.add("a");
        s1.add("b");
        s1.add("c");
        Set<String> s2 = new HashSet<>(2);
        s2.add("d");
        s2.add("b");
        s2.add("c");
        System.out.println(numElementsInCommon1(s1,s2));
        System.out.println(numElementsInCommon2(s1,s2));
    }
}
