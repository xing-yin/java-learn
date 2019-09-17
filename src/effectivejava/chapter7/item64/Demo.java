package effectivejava.chapter7.item64;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class Demo {

    /**
     * 如果你养成了使用接口作为类型的习惯，那么你的程序将更加灵活。
     * 为什么要更改实现类型呢？
     * =====》因为新的实现比原来的实现提供了更好的性能，或者因为它提供了原来的实现所缺乏的理想功能。
     */

    /**
     * 如果没有合适的接口存在，那么用类引用对象是完全合适的。
     * a.值类很少在编写时考虑到多个实现。它们通常是 final 的，很少有相应的接口。
     * ======> 如 String
     * b.第二种情况是属于框架的对象，框架的基本类型是类而不是接口。
     * ======> 如在 java.io 类中许多诸如 OutputStream 之类。
     * c.实现接口但同时提供接口中不存在的额外方法的类
     * ======> 如，PriorityQueue 有一个在 Queue 接口上不存在的比较器方法。
     */

    /**
     * 总之，在实际应用中，给定对象是否具有适当的接口应该是显而易见的。
     * 如果是这样，如果使用接口引用对象，程序将更加灵活和流行。
     * 如果没有合适的接口，就使用类层次结构中提供所需功能的最底层的类。
     */
}