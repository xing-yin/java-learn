package effectivejava.chapter7.item62;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class Demo {

    /**
     * 字符串是其他值类型的糟糕替代品
     * ====>如果有合适的值类型，无论是基本类型还是对象引用，都应该使用它；如果没有，你应该写一个。
     */

    /**
     * 字符串是枚举类型的糟糕替代品
     * ====>枚举类型常量比字符串更适合于枚举类型常量。
     */

    /**
     * 字符串是聚合类型的糟糕替代品
     * ====>更好的方法是编写一个类来表示聚合，通常是一个私有静态成员类（详见第 24 条）。
     */

    /**
     * 字符串不能很好地替代 capabilities。
     * ====> 见例子 ThreadLocalDemo
     */

    /**
     * 总之，当存在或可以编写更好的数据类型时，应避免将字符串用来表示对象。
     * 如果使用不当，字符串比其他类型更麻烦、灵活性更差、速度更慢、更容易出错。
     * 字符串经常被误用的类型包括基本类型、枚举和聚合类型。
     */

}
