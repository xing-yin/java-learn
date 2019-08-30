package effectivejava.chapter4.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class Chooser<T> {

    private final List<T> choicesList;

    /**
     * Wildcard type for parameter that serves as an T producer
     * 这个构造方法只使用集合选择来生产类型 T 的值（并将它们存储起来以备后用），所以它的声明应该使用一个 extends T 的通配符类型
     * <p>
     * 例如:假你有一个 List<Integer>，并且想把它传递给 Chooser<Number> 的构造方法。 这不会与原始声明一起编译，但是它只会将限定通配符类型添加到声明中。
     */
    public Chooser(Collection<? extends T> choicesList) {
        this.choicesList = new ArrayList<>(choicesList);
    }

    public T chooseRandom() {
        Random random = ThreadLocalRandom.current();
        return choicesList.get(random.nextInt(choicesList.size()));
    }

}
