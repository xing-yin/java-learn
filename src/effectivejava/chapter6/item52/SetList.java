package effectivejava.chapter6.item52;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yinxing
 * @date 2019/9/11
 */

public class SetList {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(8);
        List<Integer> list = new ArrayList<>(8);
        for (int i = -3; i <= 3; i++) {
            set.add(i);
            list.add(i);
        }

        // 反例:无意中使用了重载方法造成错误
        for (int i = 0; i <= 3; i++) {
            set.remove(i);
            // list 重载了remove,由于 int 的自动装箱拆箱：
            // 原本预期调用 remove(Object o)方法，而实际调用 remove(int index)方法
            list.remove(i);
        }
        // set: [-1, -2, -3]
        // list: [-2, 0, 2]
        System.out.println("set: " + set);
        System.out.println("list: " + list);


        Set<Integer> set2 = new HashSet<>(8);
        List<Integer> list2 = new ArrayList<>(8);
        for (int i = -3; i <= 3; i++) {
            set2.add(i);
            list2.add(i);
        }
        // 正例:若要修复此问题
        // a.强制转换list.remove的参数为Integer类型，迫使选择正确的重载。
        // b.或者，也可以调用Integer.valueOf(i)，然后将结果传递给list.remove方法。
        for (int i = 0; i <= 3; i++) {
            set2.remove(i);
            list2.remove(Integer.valueOf(i));
        }
        // set: [-1, -2, -3]
        // list: [-1, -2, -3]
        System.out.println("set2: " + set2);
        System.out.println("list2: " + list2);
    }
}
