package effectivejava.chapter4.item30;

import java.util.function.UnaryOperator;

/**
 * @author yinxing
 * @date 2019/8/27
 */

public class GenericSingleFactory<T> {

    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identifyFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static void main(String[] args) {
        String[] strings = {"jute", "hemp", "nylon"};
        UnaryOperator<String> sameString = identifyFunction();
        for (String s : strings) {
            System.out.println(sameString.apply(s));
        }

        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identifyFunction();

        for (Number n : numbers) {
            System.out.println(sameNumber.apply(n));
        }
    }

}
