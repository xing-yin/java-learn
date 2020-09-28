package jdk.demo.java.util.list.arraylist;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alan Yin
 * @date 2020/9/27
 */

public class ArrayListDemo {

    public static void main(String[] args) {
        // 初始化方式1
        List<Integer> list1 = new ArrayList<>();
        // 初始化方式2
        List<Integer> list2 = new ArrayList<>(4);
        // 初始化方式3
        Collection<Integer> collection = new LinkedList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        List<Integer> list3 = new ArrayList<>(collection);

        int[] a1 = new int[]{1, 2, 3};
//        arrayCopyTest(a1);

//        systemCopyTest(a1);

//        System.out.println(list3.addAll(null));

        threadUnsafeTest();
    }

    /**
     * ArrayList 是线程不安全的
     */
    private static void threadUnsafeTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Integer> list = new ArrayList<>(10000);
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                addElement(list);
            });
        }
        System.out.println(list.size());
        System.out.println(list.size() == 4000);
    }

    private static void addElement(List<Integer> list) {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

    private static void systemCopyTest(int[] a1) {
        if (a1 == null) {
            return;
        }
        int[] a2 = new int[5];
        System.arraycopy(a1, 0, a2, 0, a1.length);
        System.out.println(a2.length);
        for (int i : a2) {
            System.out.println(i);
        }
    }

    private static void arrayCopyTest(int[] a1) {
        if (a1 == null) {
            return;
        }
        int[] a2 = Arrays.copyOf(a1, 4);
        System.out.println(a2.length);
        System.out.println(a1 == a2);
    }

}
