package effectivejava.chapter6.item53;

/**
 * @author yinxing
 * @date 2019/9/11
 */

public class VariableParameterClass {

    /**
     * simple use of varargs
     */
    public static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    /**
     * The WRONG way to use varargs to pass one or more arguments!
     * 一个问题是，如果客户端在没有参数的情况下调用此方法，则它在运行时而不是在编译时失败。
     * 另一个问题是它很难看。必须在 args 参数上包含显式有效性检查，除非将 min 初始化为 Integer.MAX_VALUE，否则不能使用 for-each 循环，这也很难看。
     */
//    public static int min(int... args) {
//        if (args.length == 0) {
//            throw new IllegalArgumentException("Too few arguments");
//        }
//        int min = args[0];
//        for (int i = 1; i < args.length; i++) {
//            if (args[i] < min) {
//                min = args[i];
//            }
//        }
//        return min;
//    }

    /**
     * The right way to use varargs to pass one or more arguments
     */
    public static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }
        return min;
    }

    public static void main(String[] args) {
//        /**
//         * sum
//         */
//        System.out.println(sum(1, 2, 3));
//        System.out.println(sum());
        /**
         * min1
         */
//        System.out.println(min());
        /**
         * min2
         */
        System.out.println(min(1, 2, 3, 4));
    }
}
