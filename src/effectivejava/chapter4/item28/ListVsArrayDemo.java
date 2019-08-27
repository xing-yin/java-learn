package effectivejava.chapter4.item28;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class ListVsArrayDemo {


    /**
     * 对比1:
     * 无论哪种方式，你不能把一个 String 类型放到一个 Long 类型容器中:
     * a.用一个数组，你会发现在运行时产生了一个错误；
     * b.对于列表，可以在编译时就能发现错误。
     * <p></>
     * 当然，你宁愿在编译时找出错误。
     */

    /**
     * fail at runtime
     */
    public static void array() {
        Object[] array = new Long[1];
        // Throws ArrayStoreException
        array[0] = "i don't fit in";
    }

    /**
     * Won't compile!
     */
    public static void list() {
        // Incompatible types
//        List<Object> list = new ArrayList<Long>();
//        list.add("i don't fit in");
    }

//    public static void main(String[] args) {
//        array();
//    }

    /**
     * 对比2:
     * 数组和泛型之间的第二个主要区别是数组被具体化了（reified）[JLS，4.7]。
     * 这意味着数组在运行时知道并强制执行它们的元素类型。
     *
     * 如前所述，如果尝试将一个 String 放入 Long 数组中，得到一个 ArrayStoreException 异常。
     * 相反，泛型通过擦除（erasure）来实现[JLS，4.6]。 这意味着它们只在编译时执行类型约束，并在运行时丢弃（或擦除）它们的元素类型信息。
     * 擦除是允许泛型类型与不使用泛型的遗留代码自由互操作，从而确保在 Java 5 中平滑过渡到泛型。
     */

    /**
     * Why generic array creation is illegal - won't compile!
     */
//    List<String>[] stringLists = new List<String>[1];  // (1)
//    List<Integer> intList = List.of(42);               // (2)
//    Object[] objects = stringLists;                    // (3)
//    objects[0] = intList;                              // (4)
//    String s = stringLists[0].get(0);                  // (5)


    /**
     * 总之，数组和泛型具有非常不同的类型规则:
     *
     * 数组是协变和具体化的; 泛型是不变的，运行时类型擦除的。
     * 因此，数组提供运行时类型的安全性，但不提供编译时类型的安全性，泛型反之。
     * <p></p>
     * 一般来说，数组和泛型不能很好地混合工作。如果你发现把它们混合在一起，得到编译时错误或者警告，优先应该是用列表来替换数组。
     */

}
