package topic.java8;

import java.util.Arrays;

/**
 * Lambda 演示
 *
 * @author Alan Yin
 * @date 2021/7/28
 */

public class LambdaDemo<T> {

    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();

        //========================= 加减乘除示例 ===========================================================
        // 不使用 Lambda 的示例
        MathOperation op = new MathOperation<Integer>() {
            @Override
            public Integer operation(int a, int b) {
                return 1;
            }
        };
        // 使用 Lambda
        MathOperation op1 = (a, b) -> 1;

        // 声明类型
        MathOperation addition = (int a, int b) -> a + b;
        // 不声明类型
        MathOperation subtraction = (a, b) -> a - b;

        // 使用大括号带返回语句
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };
        // 不使用大括号带返回语句
        MathOperation division = (a, b) -> a / b;

        System.out.println(" 3 + 2 =" + demo.operation(3, 2, addition));
        System.out.println(" 3 - 2 =" + demo.operation(3, 2, subtraction));
        System.out.println(" 3 * 2 =" + demo.operation(3, 2, multiplication));
        System.out.println(" 3 / 2 =" + demo.operation(3, 2, division));

        System.out.println(" 3 ^ 2 =" + demo.operation(3, 2, (a, b) -> Math.pow(a, b)));

        //====================================================================================
        Runnable task = () -> System.out.println(666);

        // 不用括号
        GreetingService greetingService1 = message -> System.out.println(message);
        // 使用括号
        GreetingService greetingService2 = (message) -> {
            System.out.println(message);
        };

        // 函数方法引用
        GreetingService greetingService3 = System.out::println;

        greetingService1.sayMessage("Alan");
        greetingService2.sayMessage("Bob");
        greetingService3.sayMessage("Cindy");

        Arrays.asList(1, 2, 3).forEach(i -> System.out.println(i + 1));
        Arrays.asList(1, 2, 3).forEach(LambdaDemo::println);
    }

    /**
     * 由于 System.out::println 无法传参数，所以我们可以封装一个新函数
     *
     * @param integer
     */
    private static void println(Integer integer) {
        // 可以做一些更加复杂的操作，这里演示只加 1
        System.out.println(integer + 1);
    }

    interface MathOperation<T> {
        /**
         * 函数方法的签名：返回类型+方法名+参数类型列表
         *
         * @param a
         * @param b
         * @return
         */
        T operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private <T> T operation(int a, int b, MathOperation<T> mathOperation) {
        return mathOperation.operation(a, b);
    }

}
