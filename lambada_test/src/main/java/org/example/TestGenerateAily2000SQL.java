package org.example;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/1/24
 **/
public class TestGenerateAily2000SQL {

  public static void main(String[] args) {
    StringBuilder sql = new StringBuilder();
    sql.append(
        "INSERT INTO test_aily_table (`text`, `bigint`, `float`, `decimal`, `datetime`, `double`, `email`, `bool`) VALUES ");

    for (int i = 0; i < 1000; i++) {
      sql.append("('测试数据").append(i + 1).append("', ").append(10 + i).append(", ")
          .append(1.1 + i).append(", ").append(2 + i).append(", ")
          .append("CURRENT_TIMESTAMP, ").append(70.5 + i).append(", ")
          .append("'test").append(i + 1).append("@bytedance.com', ")
          .append(i % 2 == 0 ? "1" : "0").append(")");

      if (i < 999) {
        sql.append(", ");
      }
    }
    String insertSql = sql.toString();
    System.out.println("生成的INSERT语句：");
    System.out.println(insertSql);
  }
}
