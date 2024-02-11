package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/2/11
 **/
public class BottleTest {

  @Test
  public void verse_should_be_success() {
    String want = "99 bottles of beer on the wall, " +
        "99 bottles of beer.\n" +
        "Take one down and pass it around, " +
        "98 bottles of beer on the wall.\n";
    assertEquals(want, new Bottles().verse(99));
  }

  @Test
  public void another_verse_should_be_success() {
    String want = "3 bottles of beer on the wall, " +
        "3 bottles of beer.\n" +
        "Take one down and pass it around, " +
        "2 bottles of beer on the wall.\n";
    assertEquals(want, new Bottles().verse(3));
  }

  @Test
  public void verse_2_should_be_success() {
    String want = "2 bottles of beer on the wall, " +
        "2 bottles of beer.\n" +
        "Take one down and pass it around, " +
        "1 bottle of beer on the wall.\n";
    assertEquals(want, new Bottles().verse(2));
  }

  @Test
  public void verse_1_should_be_success() {
    String want = "1 bottles of beer on the wall, " +
        "1 bottles of beer.\n" +
        "Take it down and pass it around, " +
        "no more bottles of beer on the wall.\n";
    assertEquals(want, new Bottles().verse(1));
  }

  @Test
  public void verse_0_should_be_success() {
    String want = "No more bottles of beer on the wall, " +
        "No more bottles of beer.\n" +
        "Go to the store and buy some more, " +
        "99 bottles of beer on the wall.\n";
    assertEquals(want, new Bottles().verse(0));
  }

  @Test
  public void a_couple_verse_should_be_success() {
    String want =  "99 bottles of beer on the wall, " +
        "99 bottles of beer.\n" +
        "Take one down and pass it around, " +
        "98 bottles of beer on the wall.\n" +
        "\n" +
        "98 bottles of beer on the wall, " +
        "98 bottles of beer.\n" +
        "Take one down and pass it around, " +
        "97 bottles of beer on the wall.\n";
    assertEquals(want, new Bottles().verses(99,98));
  }
}
