package org.example;

import static org.example.StreamDemo.getAuthors;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.example.data.Author;

interface UseString {

  String use(String str, int start, int length);
}

/**
 * <p>函数式接口示例</p>
 *
 * @author yinxing
 * @since 2023/11/17
 **/
public class FunctionalInterfaceDemo {

  public static void main(String[] args) {
    List<Author> authors = getAuthors();

//    authors.stream()
//        .filter(author -> author.getAge() > 17 && author.getName().length() > 1)
//        .forEach(author -> System.out.println(author.getName()));

//    authors.stream()
//        .filter(((Predicate<Author>) author -> author.getAge() > 17).and(
//            author -> author.getName().length() > 1))
//        .forEach(author -> System.out.println(author.getName()));

//    authors.stream()
//        .filter(((Predicate<Author>) author -> author.getAge() > 17).or(
//            author -> author.getName().length() < 2))
//        .forEach(author -> System.out.println(author.getName()));
//
//    printNum(value -> value % 2 == 0, value -> value > 4);

//    authors.stream()
//        .filter(((Predicate<Author>) author -> author.getAge() > 17).negate())
//        .forEach(author -> System.out.println(author.getName()));

//    Stream<Author> authorStream = authors.stream();
//    authorStream.map(author -> author.getAge())
//            .map(age -> String.valueOf(age));
//
//    authorStream.map(author -> author.getAge())
//        .map(String::valueOf);

//    Stream<Author> authorStream = authors.stream();
//    StringBuilder sb = new StringBuilder();
//    authorStream.map(author -> author.getName())
//        .forEach(name -> sb.append(name));
//
//    authorStream.map(author -> author.getName())
//        .forEach(sb::append);

//    subAuthorName("测试内容", new UseString() {
//      @Override
//      public String use(String str, int start, int length) {
//        return str.substring(start, length);
//      }
//    });
//    subAuthorName("测试内容", (str, start, length) -> str.substring(start, length));

//    Stream<Author> authorStream = authors.stream();
//    authorStream.map(author -> author.getAge())
//        .map(name -> new StringBuilder(name))
//        .map(sb -> sb.append("-Test").toString())
//        .forEach(str -> System.out.println(str));
//
//    authorStream.map(author -> author.getAge())
//        .map(StringBuilder::new)
//        .map(sb -> sb.append("-Test").toString())
//        .forEach(str -> System.out.println(str));

//    authors.stream()
//        .map(Author::getAge)
//        .map(age -> age + 10)
//        .filter(age -> age > 18)
//        .map(age -> age + 2)
//        .forEach(System.out::println);
//
//    authors.stream()
//        .mapToInt(Author::getAge)//经过这一步，后续操作的都是 int 类型
//        .map(age -> age + 10)
//        .filter(age -> age > 18)
//        .map(age -> age + 2)
//        .forEach(System.out::println);

    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//    Optional<Integer> sumOptional1 = stream.filter(num -> num > 4)
//        .reduce((result, element) -> result + element);
//    sumOptional1.ifPresent(System.out::println);
//
//    Optional<Integer> sumOptional2 = stream.parallel().filter(num -> num > 4)
//        .reduce((result, element) -> result + element);
//    sumOptional2.ifPresent(System.out::println);

//    authors.parallelStream()
//        .map(Author::getAge)
//        .map(age -> age + 10)
//        .filter(age -> age > 18)
//        .map(age -> age + 2)
//        .forEach(System.out::println);

    Optional<Integer> sumOptional3 = stream.parallel()
        .peek(new Consumer<Integer>() {
          @Override
          public void accept(Integer num) {
            System.out.println(num + "-" + Thread.currentThread().getName());
          }
        }).filter(num -> num > 4)
        .reduce((result, element) -> result + element);
    sumOptional3.ifPresent(System.out::println);
  }

  public static void printNum(IntPredicate predicate, IntPredicate predicate2) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 6, 7, 8, 10};
    for (int i : arr) {
      // 相当于两个 predicate 必须同时满足才能生效
      if (predicate.and(predicate2).test(i)) {
        System.out.println(i);
      }
    }
  }

  public static String subAuthorName(String str, UseString useString) {
    int start = 0;
    int length = 1;
    return useString.use(str, start, length);
  }

}
