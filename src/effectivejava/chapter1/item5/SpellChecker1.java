package effectivejava.chapter1.item5;

import java.util.ArrayList;
import java.util.List;

/**
 * 【反例1】坏代码的味道 （将此类作为静态实用工具）
 * <p>
 * 例如:拼写检查器依赖于字典，字典是依赖的对象，并不是反例中所想象的认为是只依赖一种/个词典
 *
 * @author yinxing
 * @date 2019/8/13
 */


/**
 * Inappropriate use of static utility - inflexible & untestable
 */
public class SpellChecker1 {

//    private static final Lexicon dictionary = ...;

    /**
     * Noninstantiable
     */
    private SpellChecker1() {
    }

    public static boolean isValid(String word) {
        /// ...
        return false;
    }

    public static List<String> suggestions(String typo) {
        // ...
        return new ArrayList<>();
    }
}
