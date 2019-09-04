package effectivejava.chapter4.item34;

/**
 * @author yinxing
 * @date 2019/9/3
 */

public class NonEnumClass {

    /**
     * 反例1:没有枚举时，使用整型常量
     * The int enum pattern - severely deficient!
     * 整型枚举模式 - 严重不足
     */
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    /**
     * 这种被称为 int 枚举模式的技术有许多缺点。 它没有提供类型安全的方式，也没有提供任何表达力。
     * 如果你将一个 Apple 传递给一个需要 Orange 的方法，那么编译器不会出现警告，还会用 == 运算符比较 Apple 与 Orange，或者更糟糕的是：
     * <p></>
     * Tasty citrus flavored applesauce!:美味的橘子苹果酱
     */
    int i = (APPLE_FUJI - ORANGE_TEMPLE)/APPLE_PIPPIN;

}
