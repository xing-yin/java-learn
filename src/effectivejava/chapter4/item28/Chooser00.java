package effectivejava.chapter4.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class Chooser00<T> {

    private final List<T> choicesList;

    public Chooser00(Collection<T> choicesList) {
        this.choicesList = new ArrayList<>(choicesList);
    }

    public T chooseRandom() {
        Random random = ThreadLocalRandom.current();
        return choicesList.get(random.nextInt(choicesList.size()));
    }

}
