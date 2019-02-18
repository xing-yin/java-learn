package CSNote.basis;

import java.util.*;

/**
 * @author yinxing
 * @date 2019/1/31
 */

public class ArraysTest {

    public static void main(String[] args) {
//        String[] str = new String[]{"you","are","clever"};
//        List<String> list = Arrays.asList(str);
////        list.add("test");
//        System.out.println(list);
//        str[0]="change";
//        System.out.println(list);

        //=====================================================
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equalsIgnoreCase("1")) {
                iterator.remove();
            }
        }
        System.out.println(list);

//        //错误方式
//        for (String item : list) {
//            if ("1".equals(item)) {
//                list.remove(item);
//            }
//        }

        //=====================================================
        Map<Integer, String> map = new HashMap<Integer, String>(16);
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");

        // 方式1
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
        }

        // 方式2
        for(Integer key: map.keySet()){
            System.out.println("key:" + key);
        }

        // 方式3
        for(String value:map.values()){
            System.out.println("value:" + value);
        }

        // 方式4
        Map map2 = new HashMap();
        Iterator entries = map2.entrySet().iterator();
        while (entries.hasNext()) {

            Map.Entry entry = (Map.Entry) entries.next();

            Integer key = (Integer)entry.getKey();

            Integer value = (Integer)entry.getValue();

            System.out.println("Key = " + key + ", Value = " + value);
        }


    }
}
