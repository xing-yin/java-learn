package effectivejava.chapter6.item55;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class Demo {

    /**
     * 总之，如果发现自己编写的方法不能总是返回值，并且认为该方法的用户在每次调用时考虑这种可能性很重要，那么或许应该返回一个 Optional 的方法。
     * 但是，应该意识到，返回 Optional 会带来实际的性能后果；对于性能关键的方法，最好返回 null 或抛出异常。
     * 最后，除了作为返回值之外，不应该在任何其他地方中使用 Optional。
     */
}
