package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/2/25
 **/
public class Test {


  public static List<MyInputParam> hybridThreeSequentialSyncTestConfig() {
    List<MyInputParam> list = new ArrayList<>();
    list.add(new MyInputParam("aaa"));
    list.add(new MyInputParam("bbb"));
    return list;
  }

  private static Stream<Arguments> provideHybridThreeSequentialSyncTestConfig() {
    List<MyInputParam> testCases = hybridThreeSequentialSyncTestConfig();
    return testCases.stream().map(Arguments::of);
  }

  @SneakyThrows
  @ParameterizedTest
  @MethodSource("provideHybridThreeSequentialSyncTestConfig")
  public void hybridThreeSequentialSyncSucceed(MyInputParam inputParam) {
    System.out.println(inputParam);
  }

  public static class MyInputParam {

    String name;

    public MyInputParam(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "MyInputParam{" +
          "name='" + name + '\'' +
          '}';
    }
  }
}
