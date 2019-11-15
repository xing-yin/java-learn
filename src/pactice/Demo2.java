package pactice;

import java.util.Arrays;
import java.util.List;

/**
 * List
 *
 * @author yinxing
 * @date 2019/11/15
 */

public class Demo2 {

    public static void main(String[] args) {
        /**
         * 在 List 中保存 null 值，利用 indexOf() 方法也是可以比较获取到对应的索引值的（也就是说 null == null 为 true）
         */
        List list1 = Arrays.asList(1, 2, null, 4);
        System.out.println(list1.indexOf(null));
        System.out.println(null==null);
    }
}
