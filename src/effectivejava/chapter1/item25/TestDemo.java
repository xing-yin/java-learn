package effectivejava.chapter1.item25;

/**
 * 将源文件限制为单个顶级类
 * <p>
 * 这个教训很清楚：永远不要将多个顶级类或接口放在一个源文件中。
 * 遵循这个规则保证在编译时不能有多个定义。
 * 这又保证了编译生成的类文件以及生成的程序的行为与源文件传递给编译器的顺序无关。
 *
 * @author yinxing
 * @date 2019/8/26
 */

public class TestDemo {

    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }

}
