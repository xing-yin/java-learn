package effectivejava.chapter4.item36;

import java.util.EnumSet;
import java.util.Set;

/**
 * 正例:EnumSet - a modern replacement for bit fields
 * <p>
 * 优点:
 * java.util 包提供了 EnumSet 类来有效地表示从单个枚举类型中提取的值集合。
 * 这个类实现了 Set 接口，提供了所有其他 Set 实现的丰富性，类型安全性和互操作性。
 * <p>
 * 但是在内部，每个 EnumSet 都表示为一个位矢量（bit vector）。
 * 如果底层的枚举类型有 64 个或更少的元素，并且大多数情况下，整个 EnumSet 用单个 long 表示，所以它的性能与位属性的性能相当。
 * 批量操作（如 removeAll 和 retainAll）是使用按位算术实现的，就像你为位属性手动操作一样。
 * 但是完全避免了手动位混乱的丑陋和错误倾向：EnumSet 为你做了很大的努力。
 *
 * @author yinxing
 * @date 2019/9/4
 */

public class Text1 {

    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    /**
     * Any Set could be passed in, but EnumSet is clearly best
     */
    public void applyStyles(Set<Style> styles) {
    }

    public static void main(String[] args) {
        // EnumSet 类提供了一组丰富的静态工厂，可以轻松创建集合，其中一个代码如下所示：
        Text1 text1 = new Text1();
        text1.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }

    /**
     * 总之，仅仅因为枚举类型将被用于集合中，所以没有理由用位属性来表示它。
     * EnumSet 类将位属性的简洁性和性能与条目 34 中所述的枚举类型的所有优点相结合。
     *
     * EnumSet 的一个真正缺点是，它不像 Java 9 那样创建一个不可变的 EnumSet，但是在即将发布的版本中可能会得到补救。
     * 同时，你可以用 Collections.unmodifiableSet 封装一个 EnumSet，但是简洁性和性能会受到影响。
     */
}
