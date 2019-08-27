package effectivejava.chapter4.item27;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class UncheckedDemo {

    /**
     * SuppressWarnings 注解可用于任何声明，从单个局部变量声明到整个类。
     * 始终在尽可能最小的范围内使用 SuppressWarnings 注解。
     * 通常这是一个变量声明或一个非常短的方法或构造方法。
     * 切勿在整个类上使用 SuppressWarnings 注解。
     *
     * 这样做可能会掩盖重要的警告。
     *
     * 如果无法消除未经检查的警告，并且可以证明引发该警告的代码是安全类型的，则可以在尽可能小的范围内使用  @SuppressWarnings(“unchecked”) 注解来禁止警告。
     * 记录你决定在注释中抑制此警告的理由。
     */

    /**
     * 每当使用 @SuppressWarnings(“unchecked”) 注解时，请添加注释，说明为什么是安全的。
     * 这将有助于他人理解代码，更重要的是，这将减少有人修改代码的可能性，从而使计算不安全。
     *
     * 如果你觉得很难写这样的注释，请继续思考。 毕竟，你最终可能会发现未经检查的操作是不安全的。
     */
}
