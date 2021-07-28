package topic.java8;

import java.util.Arrays;
import java.util.List;

/**
 * foreach 循环演示
 *
 * @author Alan Yin
 * @date 2021/7/28
 */

public class ForeachDemo {

    private int x = 1;

    public static void main(String[] args) {
        ForeachDemo demo = new ForeachDemo();
        demo.test();
        System.out.println(demo.x);
    }

    private void test() {
        List list = Arrays.asList(1, 2);
        int y = 0;
        list.forEach(e -> {
            x = 3;
            // can not compile
            // y = 4;
        });
    }
}
