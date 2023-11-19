package topic.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 函数式接口 Consumer 示例演示
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class ConsumerDemo {


  public static void main(String[] args) {
    List<String> list = Arrays.asList("Alan", "Bob");

    // 复杂写法
    forEachElements(list, new Consumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(s.toUpperCase());
      }
    });

    // 简易写法
    forEachElements(list, s -> System.out.println(s.toUpperCase()));
  }

  public static void forEachElements(List<String> items, Consumer<String> consumer) {
    for (String s : items) {
      // 将如何处理元素交给调用者自行决定
      consumer.accept(s);
    }
  }
}
