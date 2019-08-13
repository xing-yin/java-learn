package effectivejava.chapter1.item5;

import java.util.ArrayList;
import java.util.List;

/**
 * 【正例】好代码的味道(借助依赖注入)
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class SpellChecker {

//    private static final Lexicon dictionary = ...;
//    // 比如这里通过构造函数依赖注入
//    public SpellChecker(Lexicon lexicon) {
//        this.dictionary = Objects.requireNonNull(lexicon);
//    }

    public static boolean isValid(String word) {
        /// ...
        return false;
    }

    public static List<String> suggestions(String typo) {
        // ...
        return new ArrayList<>();
    }
}
