package effectivejava.chapter4.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 反例: 使用原始类型(如集合)
 * <p>
 * 如果你使用原始类型，则会丧失泛型的所有安全性和表达上的优势。
 *
 * @author yinxing
 * @date 2019/8/27
 */

public class Stamp {

    /**
     * Raw collection type - don't do this!
     * 原始集合类型【不指定集合中元素的类型，即（没有类型参数的泛型）】 - 不要这样做
     */

    /**
     * My stamp collection. Contains only Stamp instances.
     * 我的集邮册。仅包含标记实例。
     */
    private static final Collection stamps = new ArrayList();

    public static Collection test() {
        /**
         * Erroneous insertion of coin into stamp collection
         * 在集邮中错误地插入硬币。集邮和硬币属于不同的对象:由于未指定集合类型，这个不能在编译期发现，要在运行时才报错
         *
         * Emits "unchecked call" warning: 发出“未选中呼叫”警告
         *
         */
        stamps.add(new Coin());
        return stamps;
    }

    public static void main(String[] args) {
        Collection stamps = Stamp.test();
        // Raw iterator type - don't do this!
        for (Iterator iterator = stamps.iterator(); iterator.hasNext(); ) {
            // Throws ClassCastException
            Stamp stamp = (Stamp) iterator.next();
        }
    }

}
