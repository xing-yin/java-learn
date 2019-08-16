package effectivejava.chapter1.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类和成员的可访问性最小化
 * <p>
 * 小结:应该尽可能地减少程序元素的可访问性（在合理范围内）
 *
 * @author yinxing
 * @date 2019/8/16
 */

public class ClassFieldMinAccessibility {

    /**
     * 反例1:potential security hold
     * <p>
     * 非零长度的数组总是可变的，所以类具有公共静态 final 数组字段，或返回这样一个字段的访问器是错误的。
     * 如果一个类有这样的字段或访问方法，客户端将能够修改数组的内容。 这是安全漏洞的常见来源：
     */
    public static final String[] VALUES = {"..."};

    /**
     * 解决办法1:使公共数组私有并添加一个公共的不可变列表：
     */
    private static final String[] PRIVATE_VALUES1 = {"..."};

    public static final List<String> VALUES1 = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES1));

    /**
     * 解决办法3:使公共数组私有并添加一个返回私有数组拷贝的方法：
     */
    private static final String[] PRIVATE_VALUES2 = {"..."};

    public static final String[] values() {
        return PRIVATE_VALUES2.clone();
    }

}
