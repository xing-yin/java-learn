package effectivejava.chapter7.item57;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * for 循环优先于 while 循环
 *
 * @author yinxing
 * @date 2019/9/12
 */

public class LoopExample {

    /**
     * 正例:
     */
    public void useForLoop() {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        // Idiom for iterating when you need the iterator
        // 需要迭代器时用于迭代的习惯用法
        for (Iterator<Integer> i = integerList.iterator(); i.hasNext(); ) {
            Integer number = i.next();
            // do something
        }

        // Compile-time error - cannot find symbol i
        // 如果复制上面的代码,比如局部变量 i，会编译出错
        for (Iterator<Integer> i2 = integerList.iterator(); i2.hasNext(); ) {
            Integer number = i2.next();
            // do something
        }
    }

    /**
     * 反例:由于局部变量的作用域范围过大，造成一些不易发现的问题
     */
    public void useWhileLoop() {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        Iterator<Integer> i = integerList.iterator();
        while (i.hasNext()) {
            Integer number = i.next();
            // do something
        }

        Iterator<Integer> i2 = integerList.iterator();
        // bug! 编程者很容易复制上面的代码而造成误用变量 i,因为还在 i 作用域内
        while (i.hasNext()) {
            Integer number = i2.next();
            // do something
        }


    }

}
