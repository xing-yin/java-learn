package topic.java8.lambda;

import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class LambdaDemo2 {

  public static void main(String[] args) {
    printNum(new IntPredicate() {
      @Override
      public boolean test(int value) {
        return value % 2 == 0;
      }
    });

    printNum(value -> value % 2 == 0);
  }

  public static void printNum(IntPredicate predicate) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i : arr) {
      if (predicate.test(i)) {
        System.out.println(i);
      }
    }
  }

}
