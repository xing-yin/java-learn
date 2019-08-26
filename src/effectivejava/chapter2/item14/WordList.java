package effectivejava.chapter2.item14;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yinxing
 * @date 2019/8/21
 */

public class WordList {

    public static void main(String[] args) {
        // 集合内部已经存在排序
        Set<String> stringSet = new TreeSet<>();
        stringSet.add("b");
        stringSet.add("a");
        stringSet.add("c");
        Collections.addAll(stringSet, args);
        System.out.println(stringSet);
    }
}
