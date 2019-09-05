package effectivejava.chapter5.item38;

/**
 * Emulated extensible enum using an interface
 * 使用接口模拟可扩展枚举
 * <p>
 * 虽然枚举类型（BasicOperation）不可扩展，但接口类型（Operation）是可以扩展的，并且它是用于表示 API 中的操作的接口类型。
 * 你可以定义另一个实现此接口的枚举类型，并使用此新类型的实例来代替基本类型。 例如，假设想要定义前面所示的操作类型的扩展，包括指数运算和余数运算。
 *
 * @author yinxing
 * @date 2019/9/5
 */

public interface Operation {

    double apply(double x, double y);

    /**
     * 总之，虽然不能编写可扩展的枚举类型，但是你可以编写一个接口来配合实现接口的基本的枚举类型，来对它进行模拟。
     * 这允许客户端编写自己的枚举（或其它类型）来实现接口。
     * 如果 API 是根据接口编写的，那么在任何使用基本枚举类型实例的地方，都可以使用这些枚举类型实例。
     *
     * 示例: 该条目中描述的模式在 Java 类库中有所使用。例如，java.nio.file.LinkOption 枚举类型实现了 CopyOption 和 OpenOption 接口。
     */
}
