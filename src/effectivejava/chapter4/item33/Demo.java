package effectivejava.chapter4.item33;

/**
 * @author yinxing
 * @date 2019/9/3
 */

public class Demo {

    /**
     * 总之，泛型 API 的通常用法（以集合 API 为例）限制了每个容器的固定数量的类型参数。
     * <p></p>a
     * 你可以通过将类型参数放在键上而不是容器上来解决此限制。 可以使用 Class 对象作为此类型安全异构容器的键。 以这种方式使用的 Class 对象称为类型令牌。
     * <p></p>
     * 也可以使用自定义键类型。 例如，可以有一个表示数据库行（容器）的 DatabaseRow 类型和一个泛型类型 Column<T> 作为其键。
     */
}
