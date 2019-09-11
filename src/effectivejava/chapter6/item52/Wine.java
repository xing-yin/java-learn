package effectivejava.chapter6.item52;

import java.util.Arrays;
import java.util.List;

/**
 * 正例:当调用重写方法时，对象的编译时类型对执行哪个方法没有影响; 总是会执行“最具体 (most specific)”的重写方法。
 * <p>
 * 将此与重载进行比较，其中对象的运行时类型对执行的重载没有影响; 选择是在编译时完成的，完全基于参数的编译时类型。
 *
 * @author yinxing
 * @date 2019/9/11
 */

public class Wine {

    String name() {
        return "Wine";
    }

    public static void main(String[] args) {
        List<Wine> wineList = Arrays.asList(
                new Wine(),
                new SparklingWine(),
                new Champagne());
        for (Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}
