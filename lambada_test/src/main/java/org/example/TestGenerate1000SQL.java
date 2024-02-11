package org.example;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/1/24
 **/
public class TestGenerate1000SQL {

  public static void main(String[] args) {
    for (int i = 1; i <= 1000; i++) {
      String name = getRandomString(10);
      int age = ThreadLocalRandom.current().nextInt(18, 60);
      double weight = ThreadLocalRandom.current().nextDouble(50.0, 100.0);
      String birthday = getRandomBirthday();
      String email = getRandomString(8) + "@test.com";
      String tag = "tag" + i;

      String insertStatement = String.format(
          "INSERT INTO yinxing_test.test_table_0124 (name, age, weight, birthday, email, tag) " +
              "VALUES ('%s', %d, %.2f, '%s', '%s', '%s');", name, age, weight, birthday, email,
          tag);

      System.out.println(insertStatement);
    }
  }

  private static String getRandomString(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = ThreadLocalRandom.current().nextInt(chars.length());
      sb.append(chars.charAt(index));
    }
    return sb.toString();
  }

  private static String getRandomBirthday() {
    int year = ThreadLocalRandom.current().nextInt(1970, 2000);
    int month = ThreadLocalRandom.current().nextInt(1, 13);
    int day = ThreadLocalRandom.current().nextInt(1, 29);
    return String.format("%04d-%02d-%02d", year, month, day);
  }
}
