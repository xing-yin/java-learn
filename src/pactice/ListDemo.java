package pactice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2020/4/21
 */

public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.get(0)==null);
    }
}
