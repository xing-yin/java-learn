package effectivejava.chapter3.item21;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 反例：在 java 8 之后随意在接口中加入默认方法【意想不到的风险】
 *
 * @author yinxing
 * @date 2019/8/26
 */

public interface CollectionWithDefaultMethod<E> {

    /**
     * default method added to the collection interface in Java 8
     */
    /**
     * Apache 的 SynchronizedCollection 类仍然在积极维护，但在撰写本文时，并未重写 removeIf 方法。
     * 如果这个类与 Java 8 一起使用，它将继承 removeIf 的默认实现，但实际上不能保持类的基本承诺：自动同步每个方法调用。
     * 默认实现对同步一无所知，并且不能访问包含锁定对象的属性。 如果客户端在另一个线程同时修改集合的情况下调用 SynchronizedCollection 实例上的 removeIf 方法，
     * 则可能会导致 ConcurrentModificationException 异常或其他未指定的行为。
     *
     * @param filter
     * @return
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean result = false;
        for (Iterator<E> it = iterator(); it.hasNext(); ) {
            if (filter.test(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    Iterator<E> iterator();

    /**
     * 问题：
     *
     * a.许多新的默认方法被添加到 Java 8 的核心集合接口中，主要是为了方便使用 lambda 表达式（第 6 章）。
     * Java 类库的默认方法是高质量的通用实现，在大多数情况下，它们工作正常。
     * 但是，编写一个默认方法并不总是可行的，它保留了每个可能的实现的所有不变量。
     *
     * b.在默认方法的情况下，接口的现有实现类可以在没有错误或警告的情况下编译，但在运行时会失败。
     */

    /**
     * 建议:
     *
     * a.应该避免使用默认方法向现有的接口添加新的方法，除非这个需要是关键的，在这种情况下，你应该仔细考虑，以确定现有的接口实现是否会被默认的方法实现所破坏。
     * 然而，默认方法对于在创建接口时提供标准的方法实现非常有用，可以减轻实现接口的任务（详见第 20 条）。
     *
     * b.值得注意的是，默认方法不是被用来设计，来支持从接口中移除方法或者改变现有方法的签名的目的。在不破坏现有客户端的情况下，这些接口都不可能发生更改。
     */
}

/**
 * 小结:
 * 在发布之前测试每个新接口是非常重要的。 多个程序员应该以不同的方式实现每个接口。
 * 至少，你应该准备三种不同的实现。编写多个使用每个新接口的实例来执行各种任务的客户端程序同样重要。
 * 这将大大确保每个接口都能满足其所有的预期用途。
 * <p>
 * 这些步骤将允许你在发布之前发现接口中的缺陷，但仍然可以轻松地修正它们。
 * 虽然在接口被发布后可能会修正一些存在的缺陷，但不要太指望这一点。
 */
