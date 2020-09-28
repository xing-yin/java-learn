package jdk.demo.java.util.list.arraylist.verify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2020/9/27
 */

public class MyList extends ArrayList<String> {

    /**
     * 子类重写父类的方法，返回值可以不一样
     * 但这里只能用数组类型，换成Object就不行
     * 应该算是java本身的bug
     */
    @Override
    public String[] toArray() {
        // 为了方便举例直接写死
        return new String[]{"1", "2", "3"};
    }

    /**
     * 为什么 ArrayList 的带集合参数的构造器要做以下处理？即为什么c.toArray();返回的有可能不是Object[]类型呢？
     * <p>
     * if (elementData.getClass() != Object[].class)
     * elementData = Arrays.copyOf(elementData, size, Object[].class);
     *
     * @param args
     */

    public static void main(String[] args) {
        Parent[] parents = new Son[]{};
        // 打印: class [Ljdk.demo.java.util.list.verify.Son;
        System.out.println(parents.getClass());

        List<String> list = new MyList();
        // 打印: class [Ljava.lang.String;
        System.out.println(list.toArray().getClass());

        // 打印: false
        System.out.println(list.toArray().getClass() == Object[].class);
    }
}
