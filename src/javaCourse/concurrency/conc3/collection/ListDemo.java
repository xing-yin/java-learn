package javaCourse.concurrency.conc3.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Alan Yin
 * @date: 2022/1/10
 */
public class ListDemo {

    public static void main(String[] args) {
//        testArraysAsList();

        testCollectionsUnmodifiableList();
    }

    private static void testCollectionsUnmodifiableList() {
        User user1 = new User("Alan", 20);
        User user2 = new User("Bob", 21);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        List<User> list2 = Collections.unmodifiableList(list);
        System.out.println(list2);
        // 不允许添加
        // list2.add(new User("Cindy", 23)); // java.lang.UnsupportedOperationException
        // 不允许删除
        // list2.remove(0);// java.lang.UnsupportedOperationException

        User user3 = new User("Davide", 24);
        // 允许 set 替换元素
        // list2.set(0, user3);// java.lang.UnsupportedOperationException

        // 允许元素内部的属性变更
        user2.setAge(26);
        System.out.println(list);
    }

    private static void testArraysAsList() {
        User user1 = new User("Alan", 20);
        User user2 = new User("Bob", 21);
        List<User> list = Arrays.asList(user1, user2);
        System.out.println(list);
        // 不允许添加
        // list.add(new User("Cindy",23)); // java.lang.UnsupportedOperationException
        // 不允许删除
        // list.remove(0);// java.lang.UnsupportedOperationException

        User user3 = new User("Davide", 24);
        // 允许 set 替换元素
        list.set(0, user3);
        System.out.println(list);

        // 允许元素内部的属性变更
        user2.setAge(26);
        System.out.println(list);
    }
}
