package effectivejava.chapter1.item11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yinxing
 * @date 2019/8/20
 */

/**
 * 理想情况下，hash 方法为集合中不相等的实例均匀地分配 int 范围内的哈希码。实现这种理想情况可能是困难的。
 * 幸运的是，要获得一个合理的近似的方式并不难。 以下是一个简单的配方：
 * <p>
 * 1.声明一个 int 类型的变量 result，并将其初始化为对象中第一个重要属性 c 的哈希码;
 * 2.对于对象中剩余的重要属性 f，请执行以下操作：
 * a. 比较属性 f 与属性 c 的 int 类型的哈希码：
 * -- i. 如果这个属性是基本类型的，使用 Type.hashCode(f) 方法计算，其中 Type 类是对应属性 f 基本类型的包装类。
 * -- ii. 如果该属性是一个对象引用，并且该类的 equals 方法通过递归调用 equals 来比较该属性，并递归地调用 hashCode 方法。 如果需要更复杂的比较，则计算此字段的“范式（“canonical representation）”，并在范式上调用 hashCode。 如果该字段的值为空，则使用 0（也可以使用其他常数，但通常来使用 0 表示）。
 * -- iii. 如果属性 f 是一个数组，把它看作每个重要的元素都是一个独立的属性。 也就是说，通过递归地应用这些规则计算每个重要元素的哈希码，并且将每个步骤 2.b 的值合并。 如果数组没有重要的元素，则使用一个常量，最好不要为 0。如果所有元素都很重要，则使用 Arrays.hashCode 方法。
 * b. 将步骤 2.a 中属性 c 计算出的哈希码合并为如下结果：result = 31 * result + c;a
 * 3.返回 result 值。
 * <p>
 * 见下面的示例:
 */

/**
 * 注意：
 * 1.不要试图从哈希码计算中排除重要的属性来提高性能。
 *      由此产生的哈希函数可能运行得更快，但其质量较差可能会降低哈希表的性能，使其无法使用。
 * 2.不要为 hashCode 返回的值提供详细的规范，因此客户端不能合理地依赖它； 你可以改变它的灵活性。
 *      Java 类库中的许多类（例如 String 和 Integer）都将 hashCode 方法返回的确切值指定为实例值的函数。
 *      这不是一个好主意，而是一个我们不得不忍受的错误：它妨碍了在未来版本中改进哈希函数的能力。
 */

public final class PhoneNumber {

    private final int areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "aera code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 999, "line num");
    }

    private static int rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ":" + val);
        }
        return (int) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber pn = (PhoneNumber) o;
        return pn.areaCode == areaCode
                && pn.prefix == prefix
                && pn.lineNum == lineNum;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(areaCode);
        result = 31 * result + Integer.hashCode(prefix);
        result = 31 * result + Integer.hashCode(lineNum);
        return result;
    }

    /**
     * Objects 类有一个静态方法，它接受任意数量的对象并为它们返回一个哈希码。
     * 可以让你编写一行 hashCode 方法。不幸的是，它们的运行速度更慢，因为它们需要创建数组以传递可变数量的参数，以及如果任何参数是基本类型，则进行装箱和取消装箱。
     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(areaCode,prefix,lineNum);
//    }

    /**
     * 如果一个类是不可变的，并且计算哈希码的代价很大，那么可以考虑在对象中缓存哈希码，而不是在每次请求时重新计算哈希码。
     */
//    // hashcode method with lazily initialized cached hash code
//    // Automatically initialized to 0
//    private int hashcode;
//
//    @Override
//    public int hashCode() {
//        int result = hashcode;
//        if (result == 0) {
//            result = Integer.hashCode(areaCode);
//            result = 31 * result + Integer.hashCode(prefix);
//            result = 31 * result + Integer.hashCode(lineNum);
//        }
//        return result;
//    }
    public static void main(String[] args) {
        Map<PhoneNumber, String> phoneNumberMap = new HashMap(2);
        phoneNumberMap.put(new PhoneNumber(707, 869, 539), "jack");
        System.out.println(phoneNumberMap.get("jack"));
    }

}
