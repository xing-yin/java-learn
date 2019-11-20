package effectivejava.chapter1.item7;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 容易发生内存泄漏情形2:缓存,一旦将对象引用放入缓存中，很容易忘记它的存在，并且在它变得无关紧要之后，仍然保留在缓存中
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class Cache {

    /**
     * 1.如果正好想实现了一个缓存：只要在缓存之外存在对某个项（entry）的键（key）引用，那么这项就是明确有关联的，就可以用 WeakHashMap 来表示缓存；
     * 这些项在过期之后自动删除。
     * <p>
     * 记住:只有当缓存中某个项的生命周期是由外部引用到键（key）而不是值（value）决定时，WeakHashMap 才有用。
     */
    public WeakHashMap cache1 = new WeakHashMap();

    /**
     * 2.缓存项有用的生命周期不太明确，随着时间的推移一些项变得越来越没有价值。在这种情况下，缓存应该偶尔清理掉已经废弃的项。
     * 这可以通过一个后台线程 (也许是 ScheduledThreadPoolExecutor) 或将新的项添加到缓存时顺便清理。
     * <p>
     * （LinkedHashMap 类使用它的 removeEldestEntry 方法实现了后一种方案。）
     */
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    LinkedHashMap cache2 = new LinkedHashMap();

    public void deleteInValidCache() {
        cache2.remove("inValid key");
    }

}
