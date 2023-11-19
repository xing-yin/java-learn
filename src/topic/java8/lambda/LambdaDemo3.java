package topic.java8.lambda;

import java.util.function.Function;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/11/16
 **/
public class LambdaDemo3 {

  public static void main(String[] args) {
    typeConver(new Function<String, Integer>() {
      @Override
      public Integer apply(String s) {
        return Integer.valueOf(s);
      }
    });

    typeConver((s) -> Integer.valueOf(s));

    typeConver(Integer::valueOf);

    typeConver(new Function<String, String>() {
      @Override
      public String apply(String s) {
        return s + "aaa";
      }
    });

    typeConver((s) -> s + "aaa");
  }


  public static <R> R typeConver(Function<String, R> function) {
    String str = "1235";
    R result = function.apply(str);
    return result;
  }


}
