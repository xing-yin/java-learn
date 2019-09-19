package effectivejava.chapter8.item69;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author yinxing
 * @date 2019/9/19
 */

public class ApiDesignDemo {

    /**
     * 如果类中具有「状态相关」（state-dependent）的方法，即只有在特定的不可预知的条件下才可以被调用的方法，
     * 这个类往往也应该具有一个单独的「状态测试」（state-testing）方法，即表明是否可以调用这个状态相关的方法。
     */
    public void goodExample() {
        Collection<Integer> list = Arrays.asList(1, 2, 3, 4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     *
     */
    public void badExample() {
        Collection<Integer> list = Arrays.asList(1, 2, 3, 4);
        Iterator<Integer> iterator = list.iterator();
        try {
            while (true) {
                System.out.println(iterator.next());
            }
        } catch (NoSuchElementException e) {
        }
    }
}
