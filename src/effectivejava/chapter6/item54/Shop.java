package effectivejava.chapter6.item54;

import java.util.*;

/**
 * @author yinxing
 * @date 2019/8/28
 */

public class Shop {

    /**
     * Returns null to indicate an empty collection. Don't do this!
     */
    private final List<Cheese> chesseList = new ArrayList<>(10);

    /**
     * 反例: 返回null
     *
     * @return a list containing all of the cheeses in the shop,
     * or null if no cheeses are available for purchase.
     */
    public List<Cheese> getCheeseList1() {
        return chesseList.isEmpty() ? null : new ArrayList<>(chesseList);
    }

    /**
     * 正例:哪怕返回结果为空，返回空数组或集合
     */
    public List<Cheese> getCheeseList() {
        return new ArrayList<>(chesseList);
    }

    /**
     * 正例:返回空数组
     * The right way to return a possibly empty array
     * 如果结果为空时，返回空数组
     */
    public Cheese[] getCheese() {
        return chesseList.toArray(new Cheese[0]);
    }

    public static void main(String[] args) {
        /**
         * 1.把没有奶酪（Cheese）可买的情况当做一种特例，这是不合常理的。这样需要在客户端中必须有额外的代码来处理 null 的返回值
         */
        Shop shop = new Shop();
        List<Cheese> chesseList = shop.getCheeseList1();
        // 这个判空处理对于客户端程序员来说容易忽视
        if (Objects.isNull(chesseList)) {
            System.out.println("Jolly good, just the thing.");
        }
    }

    /**
     * [实际上很少这样使用]
     * 如果有证据表明分配空集合会损害性能，可以通过重复返回相同的不可变空集合来避免分配，因为不可变对象可以自由共享（详见第 17 条）。
     * 下面的代码就是这样做的，使用了 Collections.emptyList 方法。如果你要返回一个 Set，可以使用 Collections.emptySet ；
     * 如果要返回 Map，则使用 Collections.emptyMap。
     * <p></p>
     * 但是请记住，这是一个优化，很少需要它。如果你认为你需要它，测量一下前后的性能表现，确保它确实有帮助：
     */
    public List<Cheese> getCheeseList0() {
        return chesseList.isEmpty() ? Collections.emptyList() : new ArrayList<>(chesseList);
    }

    /**
     * 如果你认为分配零长度数组会损害性能，则可以重复返回相同的零长度数组，因为所有零长度数组都是不可变的：
     */
    private static final Cheese[] EMPTY_CHESSE_ARRAY = new Cheese[0];

    public Cheese[] getCheese0() {
        return chesseList.toArray(EMPTY_CHESSE_ARRAY);
    }

    /**
     * 反例:不要为了提高性能而预先分配传递给 toArray 的数组。研究表明，这样做会适得其反.
     * Don’t do this - preallocating the array harms performance!
     */
    public Cheese[] getCheese1() {
        return chesseList.toArray(new Cheese[chesseList.size()]);
    }
}
