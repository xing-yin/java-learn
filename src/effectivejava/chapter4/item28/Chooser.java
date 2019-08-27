package effectivejava.chapter4.item28;

/**
 * 反例:
 *
 * @author yinxing
 * @date 2019/8/27
 */

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Chooser - a class badly in need of generics!
 * 选择器-一个非常需要泛型的类！
 */
public class Chooser {

    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    /**
     * 要使用这个类，每次调用方法时，都必须将 Object 的 choose 方法的返回值转换为所需的类型，如果类型错误，则转换在运行时失败。
     * @return
     */
    public Object chooseRandom() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
