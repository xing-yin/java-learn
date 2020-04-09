package javacore.lecture3.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2020/4/9
 */

public class FinalDemo {

    public static void testImmutable() {
        // final 只能约束 strList 这个引用不可以被赋值，但是 strList 对象行为不被 final 影响，添加元素等操作是完全正常的
        final List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");

        // 不允许操作
        //strList = new LinkedList<>();

        // 如果我们真的希望对象本身是不可变的，那么需要相应的类支持不可变的行为。
        List<String> unmodifiableList = Arrays.asList("a", "b");
        unmodifiableList.add("c");
    }

    public static void main(String[] args) {
        testImmutable();
    }
}
