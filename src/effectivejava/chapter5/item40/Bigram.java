package effectivejava.chapter5.item40;

import java.util.HashSet;
import java.util.Set;

/**
 * 总之，如果在每个方法声明中使用 Override 注解，并且认为要重写父类声明，那么编译器可以保护免受很多错误的影响。
 *
 * @author yinxing
 * @date 2019/9/6
 */

public class Bigram {

    private final char first;

    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 反例:没有使用 @Override 注解 [容易出错，很可能只是重载方法]
     * <p>
     * 错误点：重写 Object.equals()方法，其参数的类型为 Object，
     * 但 Bigram 的 equals 方法的参数不是 Object 类型的，因此 Bigram 继承 Object 的 equals 方法，
     * 这个 equals 方法测试对象的引用是否是同一个，就像 == 运算符一样。
     * 每个字母组合的 10 个副本中的每一个都与其他 9 个副本不同，所以它们被 Object.equals 视为不相等，
     */
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

    /**
     * 正例:使用 @Override 注解
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bigram)) {
            return false;
        }
        Bigram b = (Bigram) obj;
        return b.first == first && b.second == second;
    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> bigramSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                bigramSet.add(new Bigram(c, c));
            }
        }
        System.out.println("bigramSet size is:" + bigramSet.size());
    }
}
