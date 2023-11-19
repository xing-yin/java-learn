package topic.java8.lambda;

import java.util.function.IntBinaryOperator;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class LambdaDemo1 {

  public static void main(String[] args) {
    int result1 = calculateNum(new IntBinaryOperator() {
      @Override
      public int applyAsInt(int left, int right) {
        return left + right;
      }
    });

    int result2 = calculateNum((left, right) -> left + right);

    int result3 = calculateNum(Integer::sum);

    System.out.println(result1);
    System.out.println(result2);
    System.out.println(result3);
  }

  public static int calculateNum(IntBinaryOperator operator) {
    int a = 10;
    int b = 20;
    return operator.applyAsInt(a, b);
  }
}
