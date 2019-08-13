package effectivejava.chapter1.item5;

import java.util.ArrayList;
import java.util.List;

/**
 * 【反例2】坏代码的味道 （将其实现为单例）
 *
 * @author yinxing
 * @date 2019/8/13
 */


/**
 * Inappropriate use of singleton - inflexible & untestable
 */
public class SpellChecker2 {

    //    private static final Lexicon dictionary = ...;


    private SpellChecker2() {
    }

    public static SpellChecker2 INSTANCE = new SpellChecker2();

    public static boolean isValid(String word) {
        /// ...
        return false;
    }

    public static List<String> suggestions(String typo) {
        // ...
        return new ArrayList<>();
    }
}
