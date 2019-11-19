package effectivejava.chapter1.item5;

import java.util.ArrayList;
import java.util.List;

/**
 * 依赖注入优于硬连接资源(hardwiring resource)
 * <p>
 * 【正例】好代码的味道(借助依赖注入)
 * 比如在 spring 中有构造函数、setter方法完成依赖注入
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


/**
 * 尽管依赖注入极大地提高了灵活性和可测试性，但它可能使大型项目变得混乱，这些项目通常包含数千个依赖项。
 * 使用依赖注入框架（如 Dagger [Dagger]、Guice [Guice] 或 Spring [Spring]）可以消除这些混乱。
 * 请注意，为手动依赖注入而设计的 API 非常适合这些框架的使用。
 * <p>
 * 总之，不要用单例和静态工具类来实现依赖一个或多个底层资源的类，且该资源的行为会影响到该类的行为；也不要直接用这个类来创建这些资源。
 * 而应该将这些资源或者工厂传给构造器（或者静态工厂，或者构建器），通过它们来创建类。这个实践就被称作依赖注人，它极大地提升了类的灵活性、可重用性和可测试性。
 */
