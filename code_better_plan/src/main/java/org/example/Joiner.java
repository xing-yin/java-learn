package org.example;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/3/17
 **/
public class Joiner {

  private final String separator;
  private final boolean skipNulls;

  private Joiner(String separator, boolean skipNulls) {
    // 检查 separator 是否为空
    if (Objects.isNull(separator) || separator.isEmpty()) {
      throw new IllegalArgumentException("separator should not be null or empty");
    }
    this.separator = separator;
    this.skipNulls = skipNulls;
  }

  public static Joiner on(String separator) {
    return new Joiner(separator, false);
  }

  public <T> String joinToString(List<T> list) {
    // 检查入参是否为空
    if (Objects.isNull(list) || list.isEmpty()) {
      return "";
    }
    // 使用 stream 处理 入参，使用 separator 拼接结果
    return list.stream()
        .map(Object::toString).collect(Collectors.joining(separator));
  }

  public Joiner skipNulls() {
    return new Joiner(separator, true);
  }
}
