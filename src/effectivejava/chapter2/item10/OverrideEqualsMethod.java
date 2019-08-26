package effectivejava.chapter2.item10;

/**
 * 重写 equals 方法时，必须遵守它的通用约定。Object 的规范如下： equals 方法实现了一个等价关系（equivalence relation），它有以下5个属性。
 * <p>
 * 在很多情况下，不要重写 equals 方法，从 Object 继承的实现完全是你想要的。
 * 如果你确实重写了 equals 方法，那么一定要比较这个类的所有重要属性，并且以保护前面 equals 约定里五个规定的方式去比较。
 *
 * @author yinxing
 * @date 2019/8/19
 */

public class OverrideEqualsMethod {

    /**
     * 1.自反性（Reflexivity）
     *
     * 一个对象必须与自身相等
     */

    /**
     * 2.对称性（Symmetry）
     * 参见类 CaseInsensitiveString
     */

    /**
     * 3.传递性（Transitivity）
     * 参见类 Point 和 ColorPoint
     */

    /**
     * 4.一致性（Consistent）
     * 如果两个对象是相等的，除非一个（或两个）对象被修改了， 那么它们必须始终保持相等
     */

    /**
     * 5.非空性（Non-nullity）
     * 参见 NonNullityDemo
     */


    /**
     * 反例:Broken - parameter type must be Object!
     * equals 方法的参数必须是 Object
     */
//    @Override
//    public boolean equals(MyClass o) {
//
//    }

    /**
     * 写和测试 equals（和 hashCode）方法很繁琐，生的代码也很普通。
     * 替代手动编写和测试这些方法的优雅的手段是，使用谷歌 AutoValue 开源框架，该框架自动为你生成这些方法，只需在类上添加一个注解即可。
     * 在大多数情况下，AutoValue 框架生成的方法与你自己编写的方法本质上是相同的。
     */

}
