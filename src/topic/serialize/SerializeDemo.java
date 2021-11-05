package topic.serialize;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alan Yin
 * @date 2021/11/2
 */

public class SerializeDemo {

    public static void main(String[] args) {
        Set root = new HashSet();
        Set s1 = root;
        Set s2 = new HashSet();
        for (int i = 0; i < 100; i++) {
            Set t1 = new HashSet();
            Set t2 = new HashSet();
            t1.add("foo"); // 使 t2 不等于 t1
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
    }
}
