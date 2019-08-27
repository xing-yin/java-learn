package effectivejava.chapter4.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class Chooser0<T> {

    private final T[] choiceArray;

    @SuppressWarnings("unchcked")
    public Chooser0(Collection choices) {
        this.choiceArray = (T[]) choices.toArray();
    }


    /**
     * 有可能运行时出现 ClassCastException 异常
     */
    public T chooseRandom() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
