package pactice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yinxing
 * @date 2019/11/12
 */

public class Demo1 {

    private Demo1() {
    }

    public static Demo1 getInstance(){
        return new Demo1();
    }

    public void collections(){
        List list= Collections.EMPTY_LIST;
    }

    public static void main(String[] args) {
        List list = Arrays.asList("a","b");
        List list2 = Collections.emptyList();
        List list3 = null;

        for (Object s: list){
            System.out.println(s);
        }
        for (Object s: list2){
            System.out.println(s);
        }
        for (Object s: list3){
            System.out.println(s);
        }
    }
}
