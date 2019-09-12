package effectivejava.chapter7.item58;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class BadExampleDemo {

    enum Face {
        /**
         * 点数
         */
        ONE, TWO, THREE, FOUR, FIVE, SIX
    }

    static Collection<Face> FACES = Arrays.asList(Face.values());

    /**
     * 反例:外部集合的大小是内部集合大小的倍数——也许它们是相同的集合——循环将正常终止，但它不会做你想要的。
     */
    public static void test1() {
        for (Iterator<Face> i = FACES.iterator(); i.hasNext(); ) {
            for (Iterator<Face> j = FACES.iterator(); j.hasNext(); ) {
                System.out.println(i.next() + " " + j.next());
            }
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
