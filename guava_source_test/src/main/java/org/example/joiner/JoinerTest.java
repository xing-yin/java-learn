package org.example.joiner;

import static com.google.common.collect.ImmutableMap.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/3/16
 **/
public class JoinerTest {


  private static final String TARGET_FILE_PATH_NAME = "";

  private static String getDefaultValue(String s) {
    return s == null || s.isEmpty() ? "default" : s;
  }

  @Test
  public void test_join_with_null_value() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");

    Joiner joiner = Joiner.on(",");
    assertThrows(NullPointerException.class, () -> joiner.join(list));
  }

  @Test
  public void test_join_with_null_value_use_skip_null() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    // 使用jdk
    String collect = list.stream().filter(StringUtils::isNotBlank)
        .collect(Collectors.joining(";"));
    assertEquals("Harry;Ron;Hermione", collect);

    // 使用 Guava
    Joiner joiner = Joiner.on(";").skipNulls();
    String result = joiner.join(list);
    assertEquals("Harry;Ron;Hermione", result);
  }

  @Test
  public void test_join_with_null_value_with_use_for_null() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    Joiner joiner = Joiner.on(";").useForNull("is null value");
    String result = joiner.join(list);
    assertEquals("Harry;is null value;Ron;Hermione", result);
  }

  @Test
  public void test_join_append_to_StringBuilder() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder result = Joiner.on(",").useForNull("default")
        .appendTo(stringBuilder, list);

    assertSame(result, stringBuilder);
    assertEquals(stringBuilder.toString(), "Harry,default,Ron,Hermione");
    assertEquals(result.toString(), "Harry,default,Ron,Hermione");
  }

  @Test
  public void test_join_append_to_writer() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    try (FileWriter fileWriter = new FileWriter(new File(TARGET_FILE_PATH_NAME))) {
      FileWriter fileWriter1 = Joiner.on(",").useForNull("default").appendTo(fileWriter, list);
      assertTrue(Files.isFile().test(new File(TARGET_FILE_PATH_NAME)));
    } catch (IOException e) {
      fail("append to writer occur error");
    }
  }

  @Test
  public void test_join_on_with_map() {
    ImmutableMap<String, String> map = of("k1", "v1", "k2", "v2");
    String result = Joiner.on(";").withKeyValueSeparator("=").join(map);
    assertEquals(result, "k1=v1;k2=v2");
  }

  @Test
  public void test_stream_join_with_null_value_use_skip_null() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    // 使用jdk stream 实现相同的效果
    String collect = list.stream().filter(s -> s == null || s.isEmpty())
        .collect(Collectors.joining(";"));
    assertEquals("Harry;Ron;Hermione", collect);
  }

  @Test
  public void test_stream_join_with_null_value_with_use_for_null() {
    List<String> list = Arrays.asList("Harry", null, "Ron", "Hermione");
    String result = list.stream().map(JoinerTest::getDefaultValue)
        .collect(Collectors.joining(";"));
    assertEquals("Harry;default;Ron;Hermione", result);
  }
}
