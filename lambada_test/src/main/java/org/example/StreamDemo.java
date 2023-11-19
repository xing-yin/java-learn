package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.data.Author;
import org.example.data.Book;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class StreamDemo {

  public static void main(String[] args) {
    List<Author> authors = getAuthors();

    // -------------------------------------------
    List<String> collect = authors.stream()// 把集合转换为流
        .distinct()
        .filter(author -> author.getAge() < 18)
        .map(author -> author.getName())
        .collect(Collectors.toList());
//    System.out.println(collect);

    // -------------------------------------------
    Integer[] arr = {1, 2, 3, 4, 5};
    Stream<Integer> stream1 = Arrays.stream(arr);
    Stream<Integer> stream2 = Stream.of(arr);

    // -------------------------------------------
    Map<String, Integer> map = new HashMap<>();
    map.put("蜡笔小新", 19);
    map.put("黑子", 17);
    map.put("日向翔阳", 16);
    Stream<Entry<String, Integer>> stream3 = map.entrySet().stream();
//    stream3.filter(entry -> entry.getValue() > 16).forEach(entry -> System.out.println(entry));

    // -------------------------------------------
    // filter
//    authors.stream()
//        .filter(author -> author != null && author.getName().length() > 1)
//        .forEach(author -> System.out.println(author.getName()));

    // -------------------------------------------
    // map
//    authors.stream()
//        .map(author -> author.getName())
//        .forEach(name -> System.out.println(name));
//
//    authors.stream()
//        .map(author -> author.getAge())
//        .map(age -> age + 10)
//        .forEach(age -> System.out.println(age));

    // -------------------------------------------
    // distinct
//    authors.stream().distinct()
//        .forEach(author -> System.out.println(author.getName()));

    // -------------------------------------------
    // sorted
//    authors.stream()
//        .distinct()
//        .sorted((o1, o2) -> o2.getAge() - o1.getAge())
//        .forEach(author -> System.out.println(author));

    // -------------------------------------------
    // limit
//    authors.stream()
//        .distinct()
//        .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))
//        .limit(2)
//        .forEach(author -> System.out.println(author));

    // -------------------------------------------
    // skip
//    authors.stream()
//        .distinct()
//        .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))
//        .skip(1)
//        .forEach(author -> System.out.println(author));

    // -------------------------------------------
//    authors.stream()
//        .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
//        .distinct()
//        .forEach(book -> System.out.println(book.getName()));

//    authors.stream()
//        .flatMap(author -> author.getBooks().stream())
//        .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
//        .distinct()
//        .forEach(category -> System.out.println(category));

    // -------------------------------------------
    // forEach
//    authors.stream()
//        .map(author -> author.getName())
//        .distinct()
//        .forEach(name -> System.out.println(name));

    // -------------------------------------------
    // count
//    long count = authors.stream()
//        .flatMap(author -> author.getBooks().stream())
//        .distinct()
//        .count();
//    System.out.println(count);

    // -------------------------------------------
    // max&min
//    Optional<Integer> max = authors.stream()
//        .flatMap(author -> author.getBooks().stream())
//        .map(book -> book.getScore())
//        .max((o1, o2) -> o1 - o2);
//    System.out.println(max.get());
//    Optional<Integer> min = authors.stream()
//        .flatMap(author -> author.getBooks().stream())
//        .map(book -> book.getScore())
//        .min((o1, o2) -> o1 - o2);
//    System.out.println(min.get());

    // -------------------------------------------
    // collect
//    List<String> names = authors.stream()
//        .map(author -> author.getName())
//        .collect(Collectors.toList());
//    System.out.println("names:" + names);
//
//    Set<String> bookNames = authors.stream()
//        .flatMap(author -> author.getBooks().stream())
//        .map(book -> book.getName())
//        .collect(Collectors.toSet());
//    System.out.println("bookNames:" + bookNames);、
//
//    Map<String, List<Book>> map2 = authors.stream()
//        .distinct()
//        .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
//    System.out.println(map2);

    // -------------------------------------------
    // 查找与匹配
//    boolean hasAgeGt29 = authors.stream()
//        .anyMatch(author -> author.getAge() > 29);
//    System.out.println(hasAgeGt29);
//
//    boolean isAllAdult = authors.stream().allMatch(author -> author.getAge() >= 18);
//    System.out.println(isAllAdult);
//
//    boolean allAgeLt100 = authors.stream().noneMatch(author -> author.getAge() > 100);
//    System.out.println(allAgeLt100);
//
//    Optional<Author> anyGt18 = authors.stream()
//        .filter(author -> author.getAge() > 18)
//        .findAny();
//    anyGt18.ifPresent(author -> System.out.println(author.getName()));
//
//    Optional<Author> minAgeName = authors.stream()
//        .sorted((o1, o2) -> o1.getAge() - o2.getAge()).findFirst();
//    minAgeName.ifPresent(author -> author.getName());

    // -------------------------------------------
    // reduce
//    Integer sumOfAge = authors.stream()
//        .distinct()
//        .map(author -> author.getAge())
//        .reduce(0, (result, element) -> result + element);
//    System.out.println(sumOfAge);

//    Integer maxAge = authors.stream()
//        .distinct()
//        .map(author -> author.getAge())
//        .reduce(Integer.MIN_VALUE, (element1, element2) -> Math.max(element1, element2));
//    System.out.println(maxAge);
//
//    Integer minAge = authors.stream()
//        .distinct()
//        .map(author -> author.getAge())
//        .reduce(Integer.MAX_VALUE, (element1, element2) -> Math.min(element1, element2));
//    System.out.println(minAge);
//
//    Optional<Integer> minAge2 = authors.stream()
//        .distinct()
//        .map(author -> author.getAge())
//        .reduce((integer1, integer2) -> Math.min(integer1, integer2));
//    minAge2.ifPresent(age2 -> System.out.println(age2));

    authors.stream()
        .map(author -> {
          author.setAge(author.getAge() + 10);
          return author;
        })
        .map(author -> author.getAge())
        .forEach(age -> System.out.println(age));

    authors.stream()
        .map(author -> author.getAge())
        .forEach(age -> System.out.println(age));

  }

  public static List<Author> getAuthors() {
    //数据初始化
    Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
    Author author2 = new Author(2L, "亚索", 15, "狂风也追逐不上他的思考速度", null);
    Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
    Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

    //书籍列表
    List<Book> books1 = new ArrayList<>();
    List<Book> books2 = new ArrayList<>();
    List<Book> books3 = new ArrayList<>();
    books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
    books1.add(
        new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));
    books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
    books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
    books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56,
        "一个哲学家的恋爱观注定很难把他所在的时代理解"));
    books3.add(
        new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
    books3.add(new Book(6L, "风与剑", "个人传记", 100,
        "两个个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢?"));
    books3.add(new Book(6L, "风与剑", "个人传记", 100,
        "两个个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢?"));

    author.setBooks(books1);
    author2.setBooks(books2);
    author3.setBooks(books3);
    author4.setBooks(books3);

    List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
    return authorList;
  }

}
