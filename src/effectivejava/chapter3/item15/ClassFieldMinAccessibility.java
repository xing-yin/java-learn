package effectivejava.chapter3.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 使类和成员的可访问性最小化（本质就是封装）
 *
 * @author yinxing
 * @date 2019/8/16
 */

public class ClassFieldMinAccessibility {

    /**
     * 反例1:potential security hold
     * <p>
     * 非零长度的数组总是可变的，所以类具有公共静态 final 数组字段，或返回这样一个字段的访问器是错误的。
     * 如果一个类有这样的字段或访问方法，客户端将能够修改数组的内容。这是安全漏洞的常见来源：
     */
    public static final String[] VALUES = {"..."};

    /**
     * 解决办法1:使公共数组私有并添加一个公共的不可变列表：
     */
    private static final String[] PRIVATE_VALUES1 = {"..."};

    public static final List<String> VALUES1 = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES1));

    /**
     * 解决办法2:使公共数组私有并添加一个返回私有数组拷贝的方法：
     */
    private static final String[] PRIVATE_VALUES2 = {"..."};

    public static final String[] values() {
        return PRIVATE_VALUES2.clone();
    }

}

/**
 * 【封装/信息隐藏】
 * 设计良好与设计不佳的组件最重要的区别是: 隐藏内部数据和其他实现细节的程度。
 * (一个设计良好的组件隐藏了它的所有实现细节，将它的 API 与它的实现分离开来。然后，组件只通过它们的 API 进行通信，并且对彼此的内部工作一无所知。
 * 这一概念，被称为信息隐藏或封装，是软件设计的基本原则)
 * <p>
 * 访问控制机制（access control mechanism）指定了类，接口和成员的可访问性。经验很简单：`让每个类或成员尽可能地不可访问`。
 */


/**
 * 对于成员（字段、方法、嵌套类和嵌套接口），有四种可能的访问级别，在这里，按照可访问性从小到大列出：
 * <p>
 * private —— 该成员只能在声明它的顶级类内访问。
 * package-private(default) —— 成员可以从被声明的包中的任何类中访问。如果没有指定访问修饰符（接口成员除外，它默认是公共的），这是默认访问级别。
 * protected —— 成员可以从被声明的类的子类中访问（会受一些限制），以及它声明的包中的任何类。
 * public —— 该成员可以从任何地方被访问。
 */


/**
 * a.私有成员和包级私有成员都是类实现的一部分，通常不会影响其导出的 API。
 * <p>
 * b.如果一个方法重写一个超类方法，那么它在子类中的访问级别就不能低于父类中的访问级别。
 * 这对于确保子类的实例在父类的实例可用的地方是可用的（Liskov 替换原则，见条目 15）是必要的。
 * 如果违反此规则，编译器将在尝试编译子类时生成错误消息。
 * (这个规则的一个特例是，如果一个类实现了一个接口，那么接口中的所有类方法都必须在该类中声明为 public。)
 * <p>
 * c.公共类的实例字段很少采用 public 修饰（详见第 16 条）。
 *  当字段被修改时，就放弃了使用任何操作的能力，因此带有公共可变字段的类通常不是线程安全的。
 * (即使一个字段是 final 的，并且引用了一个不可变的对象，通过将其公开，你放弃了切换到一个新的内部数据表示的灵活性，而该字段并不存在)
 * <p>
 * d.同样的建议适用于静态字段，但有一个例外。
 * 假设常量是类的抽象的一个组成部分，你可以通过 public static final 字段暴露常量。
 */


/**
 * 总之，应该尽可能地减少程序元素的可访问性（在合理范围内）。
 * 在仔细设计一个最小化的公共 API 之后，你应该防止任何散乱的类，接口或成员成为 API 的一部分。
 * 除了作为常量的公共静态 final 字段之外，公共类不应该有公共字段。确保 public static final 字段引用的对象是不可变的。
 */

