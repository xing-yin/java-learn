package effectivejava.chapter7.item61;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class Demo {

    /**
     * 什么时候应该使用包装类型呢？它们有几个合法的用途。
     * a.作为集合中的元素、键和值。不能将基本类型放在集合中，因此必须使用包装类型。这是一般情况下的特例。
     * b.在参数化类型和方法中，必须使用包装类型作为类型参数，因为 Java 不允许使用基本类型。例如，不能将变量声明为 ThreadLocal<int> 类型，因此必须使用 ThreadLocal<Integer>。
     * c.最后，在进行反射方法调用时，必须使用包装类型
     */

    /**
     * 总之，只要有选择，就应该优先使用基本类型，而不是包装类型。基本类型更简单、更快。
     * 如果必须使用包装类型，请小心！自动装箱减少了使用包装类型的冗长，但没有减少危险。
     * 当你的程序使用 == 操作符比较两个包装类型时，它会执行标识比较，这几乎肯定不是你想要的。
     * 当你的程序执行包含包装类型和基本类型的混合类型计算时，它将进行拆箱，当你的程序执行拆箱时，将抛出 NullPointerException。
     * 最后，当你的程序将基本类型装箱时，可能会导致代价高昂且不必要的对象创建。
     */
}