package temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2020/8/28
 */

public class Test {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(4L);
        list.add(5L);
        list.remove(null);
        list.remove(1L);
        System.out.println(list);
    }
}
