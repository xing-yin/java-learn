package effectivejava.chapter1.item8;

/**
 * 08 避免使用 Finalizer 和 Cleaner 机制
 *
 * @author yinxing
 * @date 2019/11/20
 */

public class AvoidFinalizerOrCleaner {

    /**
     * Finalizer 机制是不可预知的，往往是危险的，而且通常是不必要的。
     * <p>
     * Java 9 中 Cleaner 机制代替了 Finalizer 机制。 Cleaner 机制不如 Finalizer 机制那样危险，但仍然是不可预测，运行缓慢并且通常是不必要的。
     */

    /**
     * 劣势1：
     * 1.Finalizer 和 Cleaner 机制的一个缺点是不能保证他们能够及时执行,甚至不能保证它们是否会运行。
     * <p>
     * a.(在一个对象变得无法访问时，到 Finalizer 和 Cleaner 机制开始运行时，这期间的时间是任意长的)
     * b.(不要相信 System.gc 和 System.runFinalization 方法。他们可能会增加 Finalizer 和 Cleaner 机制被执行的几率，但不能保证一定会执行。)
     * <p>
     * eg: 例如，依赖于 Finalizer 和 Cleaner 机制来关闭文件是严重的错误，因为打开的文件描述符是有限的资源。
     * 如果由于系统迟迟没有运行 Finalizer 和 Cleaner 机制而导致许多文件被打开，程序可能会失败，因为它不能再打开文件了。
     */

    /**
     * 劣势2：
     * 2.Finalizer 机制的另一个问题是在执行 Finalizer 机制过程中，未捕获的异常会被忽略，并且该对象的 Finalizer 机制也会终止。
     * <p>
     * a.(未捕获的异常会使其他对象陷入一种损坏的状态（corrupt state）。如果另一个线程试图使用这样一个损坏的对象，可能会导致任意不确定的行为。)
     * b.(Cleaner 机制没有这个问题，因为使用 Cleaner 机制的类库可以控制其线程。)
     */

    /**
     * 劣势3：
     * 3.使用 finalizer 和 cleaner 机制会导致严重的性能损失。
     * <p>
     * a.(主要是因为 finalizer 机制会阻碍有效的垃圾收集。)
     */

    /**
     * 劣势4：
     * finalizer 机制有一个严重的安全问题：它们会打开你的类来进行 finalizer 机制攻击。
     * <p>
     * a.(为了保护非 final 类不受 finalizer 机制攻击，编写一个 final 的 finalize 方法，让它什么都不做。)
     */

    /**
     * 劣势这么多，那应该怎样做呢？
     * <p>
     * 为对象封装需要结束的资源（如文件或线程），而不是为该类编写 Finalizer 和 Cleaner 机制：
     * 让你的类实现 AutoCloseable 接口即可，并要求客户在在不再需要时调用每个实例 close 方法，
     * 通常使用 try-with-resources 确保终止，即使面对有异常抛出情况（详见第 9 条）。
     * <p>
     * (一个值得一提的细节是实例必须跟踪是否已经关闭：close 方法必须记录在对象里不再有效的属性，其他方法必须检查该属性，
     * 如果在对象关闭后调用它们，则抛出 IllegalStateException 异常。)
     */

}

/**
 * 总之，除了作为一个`安全网`或者终止`非关键`的本地资源，不要使用 Cleaner 机制，或者是在 Java 9 发布之前的 finalizers 机制。
 * 即使是这样，也要当心不确定性和性能影响。
 */