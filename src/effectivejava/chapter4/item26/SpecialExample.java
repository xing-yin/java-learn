package effectivejava.chapter4.item26;

import java.util.Set;

/**
 * 例外：需要使用原始类型的规则
 *
 * @author yinxing
 * @date 2019/8/27
 */

public class SpecialExample {

    /**
     * 例外1：
     * 你必须在类字面值（class literals）中使用原始类型。
     * 规范中不允许使用参数化类型（尽管它允许数组类型和基本类型）[JLS，15.8.2]。
     * 换句话说，List.class，String[].class 和 int.class 都是合法的，但 List<String>.class 和 List<?>.class 不是合法的
     */

    /**
     * 例外2：涉及 instanceof 操作符
     * 因为泛型类型信息在运行时被删除，所以在无限制通配符类型以外的参数化类型上使用 instanceof 运算符是非法的。
     * 使用无限制通配符类型代替原始类型不会以任何方式影响 instanceof 运算符的行为。
     * 在这种情况下，尖括号和问号就显得多余。
     */
    /**
     * Legitimate use of raw type - instanceof operator
     */
    public void example2(Object o) {
        if (o instanceof Set) {
            // 请注意，一旦确定 o 对象是一个 Set，则必须将其转换为通配符 Set<?>，而不是原始类型 Set。
            // 这是一个强制转换，所以不会导致编译器警告。
            Set<?> set = (Set<?>) o;
        }
    }

}
