package effectivejava.chapter7.item58;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class Demo {

    /**
     * 有三种常见的情况是你不能分别使用 for-each 循环的:
     * a.有损过滤（Destructive filtering）—— 如果需要遍历集合，并删除指定选元素，则需要使用显式迭代器，以便可以调用其 remove 方法。
     *   通常可以使用在 Java 8 中添加的 Collection 类中的 removeIf 方法，来避免显式遍历。
     *
     * b.转换——如果需要遍历一个列表或数组并替换其元素的部分或全部值，那么需要列表迭代器或数组索引来替换元素的值。
     *
     * c.并行迭代——如果需要并行地遍历多个集合，那么需要显式地控制迭代器或索引变量，
     *   以便所有迭代器或索引变量都可以同步进行。
     */

    /**
     * 总之，for-each 循环在清晰度，灵活性和错误预防方面提供了超越传统 for 循环的令人注目的优势，而且没有性能损失。
     * 尽可能使用 for-each 循环优先于 for 循环。
     */
}
