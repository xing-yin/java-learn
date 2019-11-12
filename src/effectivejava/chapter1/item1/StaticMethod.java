package effectivejava.chapter1.item1;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

/**
 * 1. 考虑使用静态工厂方法替代构造方法
 * @author yinxing
 * @date 2019/8/12
 */

/**
 * 总之，静态工厂方法和公共构造方法都有它们的用途，并且了解它们的相对优点是值得的。
 * 通常，静态工厂更可取，因此避免在没有考虑静态工厂的情况下直接选择使用公共构造方法。
 */

public class StaticMethod {

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    // 提供静态工厂方法而不是公共构造方法的优缺点

    /**
     * 优点1:与构造方法不同，有名字
     * (具有精心选择名称的静态工厂更易于使用，并且生成的客户端代码更易于阅读)
     */
    public void temp1() {
        Random random = new Random();
        BigInteger.probablePrime(2, random);
    }

    /**
     * 优点2:不需要每次调用时创建一个新的对象(特别是代码比较大时)
     * (允许不可变类使用预先构建的实例，或者在构造时缓存实例，并反复使用避免重新创建相同的对象)
     */
    public void temp2() {
        Boolean.valueOf(1>2);
    }

    /**
     * 优点3:与构造方法不同，可以返回其返回类型的任何子类型的对象
     * （java 8开始，允许接口包含静态方法）
     */

    /**
     * 优点4:可以根据输入参数类型的不同而不同
     */
    public void temp3() {
        // EnumSet只有静态工厂方法
        // 在 OpenJDK 实现中，它们根据底层枚举类型的大小返回两个子类中的一个的实例：
        // 1. 大多数枚举类型具有 64 个或更少的元素，静态工厂将返回一个 RegularEnumSet 实例， 底层是long 类型；
        // 2.如果枚举类型具有六十五个或更多元素，则工厂将返回一个 JumboEnumSet 实例，底层是long 类型的数组。
        // 如果 RegularEnumSet 对于小的枚举类型不再具有性能优势，则可以在未来版本中将其淘汰，且不会产生任何不良影响。
    }

    /**
     * 优点5:在编写包含该方法的类时，返回的对象的类不需要存在
     */

    /**
     * 缺点1:没有公共或受保护构造方法的类不能被子类化（即无法被继承）
     * (这样的好处在于可以鼓励程序员使用组合而不是继承)
     */

    /**
     * 缺点2:程序员很难找到它们(需要查看文档)
     */


    /**
     下面是一些静态工厂的常用名称：

     * from —— 类型转换方法，它接受单个参数并返回此类型的相应实例，例如：Date d = Date.from(instant);
     * of —— 聚合方法，接受多个参数并返回该类型的实例，并把他们合并在一起，例如：Set faceCards = EnumSet.of(JACK, QUEEN, KING);
     * valueOf —— from 和 to 更为详细的替代 方式，例如：BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
     * instance 或 getinstance —— 返回一个由其参数 (如果有的话) 描述的实例，但不能说它具有相同的值，例如：StackWalker luke = StackWalker.getInstance(options);
     * create 或 newInstance —— 与 instance 或 getInstance 类似，除此之外该方法保证每次调用返回一个新的实例，例如：Object newArray = Array.newInstance(classObject, arrayLen);
     * getType —— 与 getInstance 类似，但是在工厂方法处于不同的类中的时候使用。getType 中的 Type 是工厂方法返回的对象类型，例如：FileStore fs = Files.getFileStore(path);
     * newType —— 与 newInstance 类似，但是在工厂方法处于不同的类中的时候使用。newType中的 Type 是工厂方法返回的对象类型，例如：BufferedReader br = Files.newBufferedReader(path);
     * type —— getType 和 newType 简洁的替代方式，例如：List litany = Collections.list(legacyLitany);
     */
    public void nameOfStaticFactory(){
        Date d = Date.from(null);
        // Set<String> faceCards = EnumSet.of(JACK, QUEEN, KING);
        BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
        // StackWalker luke = StackWalker.getInstance(options);
        Object newArray = Array.newInstance(this.getClass(),2);
        // FileStore fs = Files.getFileStore(path);
        // BufferedReader br = Files.newBufferedReader(path);
        // List list = Collections.list(Enumeration);


    }
}
