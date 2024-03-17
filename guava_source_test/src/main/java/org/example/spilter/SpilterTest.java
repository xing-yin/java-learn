package org.example.spilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/3/16
 **/
public class SpilterTest {

  @Test
  public void testSplitter() {
    String input = "a,b,\"\",,  c  ";
    Splitter splitter = Splitter.on(",")
        // 忽略空字符串
        .omitEmptyStrings()
        // 移除每个元素的前后空格
        .trimResults();
    List<String> strings = splitter.splitToList(input);
    System.out.println(strings);
    assertEquals(strings.size(), 4);
    assertEquals(Arrays.asList("a", "b", "\"\"", "c"), strings);
  }

  /**
   * 驼峰与下划线互转
   */
  @Test
  public void testCamelCase() {
    String input = "a,b,\"\",,  c  ";
  }

  /**
   * 测试集合的方法
   */
  @Test
  public void testCollection() {
    // 快速创建集合
    ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e");

    // 要求传ids,一次最多2条
    List<List<String>> partition = Lists.partition(list, 2);
    System.out.println(partition);
  }

  /**
   * Ints等primary 类型的方法测试
   */
  @Test
  public void testPrimary() {
    List<Integer> integers = Ints.asList(1, 2, 3, 4, 5);

    List<Long> longs = Longs.asList(1, 2, 3);
  }

}
