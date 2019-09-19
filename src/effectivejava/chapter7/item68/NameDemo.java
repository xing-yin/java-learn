package effectivejava.chapter7.item68;

/**
 * @author yinxing
 * @date 2019/9/19
 */

public class NameDemo {

    /**
     * 简单地说，命名分为两类: 排版和语法
     */

    /**
     下表显示了排版约定的示例：
     Identifier Type	|   Example
     Package or module	|	org.junit.jupiter.api, com.google.common.collect
     Class or Interface	|	Stream, FutureTask, LinkedHashMap,HttpClient
     Method or Field	|	remove, groupingBy, getCrc
     Constant Field	    |	MIN_VALUE, NEGATIVE_INFINITY
     Local Variable	    |	i, denom, houseNum
     Type Parameter	    |	T, E, K, V, X, R, U, V, T1, T2
     */

    /**
     * 语法命名约定比排版约定更灵活
     *
     * a.可实例化的类，包括枚举类型，通常使用一个或多个名词短语来命名，例如 Thread、PriorityQueue 或 ChessPiece。
     * b.不可实例化的实用程序类（详见第 4 条）通常使用复数名词来命名，例如 collector 或 Collections。
     * c.接口的名称类似于类，例如集合或比较器，或者以 able 或 ible 结尾的形容词，例如 Runnable、Iterable 或 Accessible。
     * d.因为注解类型有很多的用途，所以没有哪部分占主导地位。名词、动词、介词和形容词都很常见，例如，BindingAnnotation、Inject、ImplementedBy 或 Singleton。
     */

    /**
     * 方法：
     * a.执行某些操作的方法通常用动词或动词短语（包括对象）命名，例如，append 或 drawImage。
     * b.返回布尔值的方法的名称通常以单词 is 或 has（通常很少用）开头，后面跟一个名词、一个名词短语，
     *   或者任何用作形容词的单词或短语，例如 isDigit、isProbablePrime、isEmpty、isEnabled 或 hasSiblings。
     * c.返回被调用对象的非布尔函数或属性的方法通常使用以 get 开头的名词、名词短语或动词短语来命名，例如 size、hashCode 或 getTime。
     * d.转换对象类型（返回不同类型的独立对象）的实例方法通常称为 toType，例如 toString 或 toArray。
     * e.返回与接收对象类型不同的视图（详见第 6 条）的方法通常称为 asType，例如 asList。
     * f.返回与调用它们的对象具有相同值的基本类型的方法通常称为类型值，例如 intValue。
     * g.静态工厂的常见名称包括 from、of、valueOf、instance、getInstance、newInstance、getType 和 newType（
     */

    /** 字段:
     * 字段名的语法约定没有类、接口和方法名的语法约定建立得好，也不那么重要，因为设计良好的 API 包含很少的公开字段。
     * 类型为 boolean 的字段的名称通常类似于 boolean 访问器方法，省略了初始值「is」，例如 initialized、composite。
     * 其他类型的字段通常用名词或名词短语来命名，如 height、digits 和 bodyStyle。局部变量的语法约定类似于字段的语法约定，但要求更少
     */


    /**
     * 总之，将标准命名约定内在化，并将其作为第二性征来使用。
     * 排版习惯是直接的，而且在很大程度上是明确的；
     * 语法惯例更加复杂和松散。「如果长期以来的传统用法要求不遵循这些约定，就不应该盲目地遵循这些约定。」，应使用常识判断。
     */
}
