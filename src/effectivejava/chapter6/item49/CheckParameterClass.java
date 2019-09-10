package effectivejava.chapter6.item49;

import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/9/10
 */

public class CheckParameterClass {

    /**
     * Objects.requireNonNull 方法灵活方便，因此没有理由再手动执行空值检查。
     */
    public void method1(String s) {
        Objects.requireNonNull(s, "s can not be null");
    }

    /**
     * Private helper function for a recursive sort
     * 对于未导出的方法，作为包的作者，控制调用方法的环境，这样就可以并且应该确保只传入有效的参数值。
     * 因此，非公共方法可以使用断言检查其参数。
     */
    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
        // Do the computation
    }

    /**
     * 总而言之，每次编写方法或构造方法时，都应该考虑对其参数存在哪些限制。
     * 应该记在这些限制，并在方法体的开头使用显式检查来强制执行这些限制。
     * 养成这样做的习惯很重要。 在第一次有效性检查失败时，它所需要的少量工作将会得到对应的回报。
     */

}
