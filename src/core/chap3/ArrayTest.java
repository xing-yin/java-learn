package core.chap3;

import java.util.Arrays;

/**
 * @author Alan Yin
 * @date 2021/4/6
 */

public class ArrayTest {

    public static void main(String[] args) {
//        arrayTest();

//        commandLineTest(args);

        arraySortTest();
    }

    private static void arraySortTest() {
        int[] a = new int[]{4, 2, 3, 1};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 命令行测试
     * 测试1: java ArrayTest -g hello world   ==> goodBye -g hello world
     * 测试2: java ArrayTest -h hello world   ==> hello -h hello world
     *
     * @param args
     */
    private static void commandLineTest(String[] args) {
        if (args.length == 0 || args[0].equals("-h")) {
            System.out.println("hello");
        } else if (args[0].equals("-g")) {
            System.out.println("goodBye");
        }
        for (int i = 0; i < args.length; i++) {
            System.out.print(" " + args[i]);
        }
    }

    public static void arrayTest() {
        int[] a = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(a)); // 打印数组

        // 两个变量引用同一个数组
        int[] b = a;
        b[3] = 5;// 改变会影响原数组的值
        System.out.println(Arrays.toString(a));

        // 将数组所有值拷贝到新数组
        int[] c = Arrays.copyOf(a, a.length);
        c[3] = 100;// 改变不影响原数组的值
        System.out.println(Arrays.toString(a));
    }
}
