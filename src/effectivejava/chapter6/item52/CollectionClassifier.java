package effectivejava.chapter6.item52;

/**
 * 反例:没有慎重地使用重载
 *
 * @author yinxing
 * @date 2019/9/11
 */

import java.util.*;

/**
 * Broken! - What does this program print?
 */
public class CollectionClassifier {

//    /**
//     * 反例
//     */
//    public static String classify(Set<?> set) {
//        return "Set";
//    }
//
//    /**
//     * 反例
//     */
//    public static String classify(List<?> list) {
//        return "List";
//    }
//
//    /**
//     * 反例
//     * 对于循环的所有三次迭代，参数的编译时类型是相同的：Collection<?>。
//     * 运行时类型在每次迭代中都不同，但这不会影响对重载方法的选择。
//     * 因为参数的编译时类型是Collection<?>，所以唯一适用的重载是第三个classify(Collection<?> c)方法，并且在循环的每次迭代中调用这个重载。
//     */
//    public static String classify(Collection<?> collection) {
//        return "Unknown Collection";
//    }

    /**
     * 正例:修复
     * 在CollectionClassifier示例中，程序目的是通过基于参数的运行时类型自动调用适当的方法重载来辨别参数的类型。
     * 方法重载不提供此功能。
     * 假设需要一个静态方法，修复CollectionClassifier程序的最佳方法是用一个执行显式instanceof测试的方法替换classify的所有三个重载：
     */
    public static String classify(Collection<?> c) {
        return c instanceof Set ? "Set" :
                c instanceof List ? "List" : "Unknown Collection";
    }


    public static void main(String[] args) {
        /**
         * 输出:
         * Unknown Collection
         * Unknown Collection
         * Unknown Collection
         */
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String, String>(16).values()};
        for (Collection c : collections) {
            System.out.println(classify(c));
        }

    }
}
