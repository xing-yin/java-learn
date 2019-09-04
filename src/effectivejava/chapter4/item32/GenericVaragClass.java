package effectivejava.chapter4.item32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 泛型和可变参数结合
 *
 * @author yinxing
 * @date 2019/8/30
 */

public class GenericVaragClass {

    /**
     * 反例1:Mixing generics and varargs can violate type safety!
     * 泛型和可变参数可能会违反类型安全
     */
    /**
     * 这个例子引发了一个有趣的问题：为什么声明一个带有泛型可变参数的方法是合法的，当明确创建一个泛型数组是非法的时候呢？
     * 答案是，具有泛型或参数化类型的可变参数参数的方法在实践中可能非常有用，因此语言设计人员选择忍受这种不一致。
     * 事实上，Java 类库导出了几个这样的方法，包括 Arrays.asList(T... a)，
     * Collections.addAll(Collection<? super T> c, T... elements)， EnumSet.of(E first, E... rest)。
     * 与前面显示的危险方法不同，这些类库方法是类型安全的。
     */
    static void dangerous(List<String>... stringLists) {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(42));
        Object[] objects = stringLists;
        // heap pollution: 堆污染
        objects[0] = integerList;
        // ClassCastException:此方法没有可见的强制转换，但在调用一个或多个参数时抛出 ClassCastException 异常
        String s = stringLists[0].get(0);
    }

    /**
     * 反例2:UNSAFE - Exposes a reference to its generic parameter array!
     * <p>【见下面的 pickTwo()】
     * 这个方法只是返回它的可变参数数组。 该方法可能看起来并不危险，但是！
     * 该数组的类型由传递给方法的参数的编译时类型决定，编译器可能没有足够的信息来做出正确的判断。
     * 由于此方法返回其可变参数数组，它可以将堆污染传播到调用栈上。
     */
    static <T> T[] toArray(T... arg) {
        return arg;
    }

    /**
     * 反例2应用:这段代码分配了一个 Object[] 类型的数组，它是保证保存这些实例的最具体的类型，而不管在调用位置传递给 pickTwo 的对象是什么类型。
     * toArray 方法只是简单地将这个数组返回给 pickTwo，然后 pickTwo 将它返回给调用者，所以 pickTwo 总是返回一个 Object[] 类型的数组。
     */
    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(a, c);
            case 2:
                return toArray(b, c);
        }
        // can not get here
        throw new AssertionError();
    }

    /**
     * 正例1:Safe method with a generic varargs parameter
     */
    @SafeVarargs
    static <T> List<T> flattern(List<? extends T>... lists) {
        List<T> result = new ArrayList<>(lists.length);
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    /**
     * 上述例子的替代方式: 使用 SafeVarargs 注解的替代方法是采用条目 28 的建议，并用 List 参数替换可变参数（这是一个变相的数组）。
     * <p>
     * 然后可以将此方法与静态工厂方法 List.of 结合使用，以允许可变数量的参数。
     * 请注意，这种方法依赖于 List.of 声明使用 @SafeVarargs 注解：flatten(List.of(friends, romans, countrymen));
     */
    static <T> List<T> flattern2(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    /**
     * 正例1应用:
     */
//    static <T> List<T> pickTwo(T a, T b, T c) {
//        switch (ThreadLocalRandom.current().nextInt(3)) {
//            case 0:
//                return List.of(a, b);
//            case 1:
//                return List.of(a, c);
//            case 2:
//                return List.of(b, c);
//            default:
//                return Collections.emptyList();
//        }
//        // can not get here
//        throw new AssertionError();
//    }

    public static void main(String[] args) {
        // 反例1：
        // dangerous(Arrays.asList("a", "b"));
        // 反例2: ClassCastException
        String[] strings = pickTwo("a", "b", "c");
        // 正例1：
        // List<String> stringList = pickTwo("e","f","g");
    }
}
