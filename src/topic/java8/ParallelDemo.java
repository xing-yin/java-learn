package topic.java8;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 并行演示
 *
 * @author Alan Yin
 * @date 2021/7/28
 */

public class ParallelDemo {

    public static void main(String[] args) {
        // 串行执行
        testSerialize();
        // 并行执行
        testParallel();
    }

    private static void testParallel() {
        ArrayList<String> list = costTimeOperation();

        System.out.println("testParallel 并行Stream开始排序");
        long begin = System.currentTimeMillis();
        list.stream().sorted().count();
        System.out.println("testParallel 并行Stream完成排序，耗时:" + (System.currentTimeMillis() - begin));
    }

    private static ArrayList<String> costTimeOperation() {
        ArrayList<String> list = new ArrayList<>(50000000);
        for (int i = 0; i < 50000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        return list;
    }

    private static void testSerialize() {
        ArrayList<String> list = costTimeOperation();

        System.out.println("testSerialize 串行Stream开始排序");
        long begin = System.currentTimeMillis();
        list.stream().sorted().count();
        System.out.println("testSerialize 串行Stream完成排序，耗时:" + (System.currentTimeMillis() - begin));
    }


}
