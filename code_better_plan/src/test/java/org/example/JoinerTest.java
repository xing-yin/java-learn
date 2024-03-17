package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/3/17
 **/
class JoinerTest {

  @Test
  public void testJoinNormal() {
    // string
    List<String> list = Arrays.asList("a", "b", "c");
    Joiner joiner = Joiner.on(";");
    String result = joiner.joinToString(list);
    assertEquals("a;b;c", result);

    // integer
    List<Integer> list2 = Arrays.asList(1, 2, 3);
    Joiner joiner2 = Joiner.on(";");
    String result2 = joiner2.joinToString(list2);
    assertEquals("1;2;3", result2);
  }

  @Test
  public void testShouldExceptionWhenSeparatorIsNull() {
    assertThrows(IllegalArgumentException.class, () -> Joiner.on(null));
    assertThrows(IllegalArgumentException.class, () -> Joiner.on(""));
  }

  @Test
  public void testJoinWithNullValueShouldThrowNullPointerException() {
    // string
    List<String> list = Arrays.asList("a", null, "b", "c");
    Joiner joiner = Joiner.on(";");
    assertThrows(NullPointerException.class, () -> {
      joiner.joinToString(list);
    });
  }

  @Test
  public void testJoinSkipNullValue() {
//    // string
//    List<String> list = Arrays.asList("a", null, "b", "c");
//    Joiner joiner = Joiner.on(";").skipNulls();
//    String result = joiner.joinToString(list);
//    assertEquals("a;b;c", result);
  }


}