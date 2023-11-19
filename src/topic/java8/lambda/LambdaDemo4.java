package topic.java8.lambda;

import java.util.function.IntConsumer;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class LambdaDemo4 {

  public static void main(String[] args) {
    foreachArr(new IntConsumer() {
      @Override
      public void accept(int value) {
        System.out.println(value);
      }
    });

    foreachArr(i -> System.out.println(i));

    foreachArr(System.out::println);
  }

  public static void foreachArr(IntConsumer consumer) {
    int[] arr = {1, 2, 3, 4, 5, 6, 5, 6, 7, 8, 9, 10};
    for (int i : arr) {
      consumer.accept(i);
    }
  }

}
