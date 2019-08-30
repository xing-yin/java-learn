package effectivejava.chapter4.item31;

/**
 * @author yinxing
 * @date 2019/8/28
 */

public class WildcardTypeDemo {

    /**
     * 这里有一个助记符来帮助你记住使用哪种通配符类型： PECS 代表： producer-extends，consumer-super。
     *
     * 换句话说，如果一个参数化类型代表一个 T 生产者，使用 <? extends T>；如果它代表 T 消费者，则使用 <? super T>。
     *
     * 在我们的 Stack 示例中，pushAll 方法的 src 参数生成栈使用的 E 实例，因此 src 的合适类型为 Iterable<? extends E>；
     * popAll 方法的 dst 参数消费 Stack 中的 E 实例，因此 dst 的合适类型是 Collection <? super E>。
     * PECS 助记符抓住了使用通配符类型的基本原则。 Naftalin 和 Wadler 称之为获取和放置原则。
     */
}
