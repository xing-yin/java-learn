package pactice;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author Alan Yin
 * @date 2020/4/22
 */

public class MapDemo {

    public static void main(String[] args) {
        TreeMap<Long, Long> map = new TreeMap<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });
//        map.put(3L,4L);
//        map.put(2L,3L);
//        map.put(1L,2L);

        map.put(1L,2L);
        map.put(2L,3L);
        map.put(3L,4L);
        System.out.println(map);
    }
}
