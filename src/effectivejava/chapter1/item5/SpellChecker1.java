package effectivejava.chapter1.item5;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态工具类和单例类不适合与需要引用底层资源的类。
 * <p>
 * 【反例1】坏代码的味道 （将此类作为静态实用工具）
 * <p>
 * 例如:拼写检查器依赖于字典，字典是依赖的对象，并不是反例中所想象的认为是只依赖一种/个词典
 *
 * @author yinxing
 * @date 2019/8/13
 */


/**
 * Inappropriate use of static utility - inflexible & untestable
 * 不合适的方式使用静态工具类 - 不灵活也不可测试
 */
public class SpellChecker1 {

//    private static final Lexicon dictionary = ...;

    /**
     * Noninstantiable
     */
    private SpellChecker1() {
    }

    public static boolean isValid(String word) {
        /// code...
        return false;
    }

    public static List<String> suggestions(String typo) {
        // ...
        return new ArrayList<>();
    }
}

/**
 * 这两种方法都不令人满意，因为他们都是假设只有一本字典可用。
 * 实际上，每种语言都有自己的字典，特殊的字典被用于特殊的词汇表。
 * 另外，可能还需要用特殊的词典进行测试。想当然地认为一本字典就足够了，这是一厢情愿的想法。
 */
