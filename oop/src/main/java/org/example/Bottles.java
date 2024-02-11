package org.example;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/2/11
 **/
public class Bottles {

  public String verse(int number) {
    switch (number) {
      case 0:
        return "No more bottles of beer on the wall, " +
            "No more bottles of beer.\n" +
            "Go to the store and buy some more, " +
            "99 bottles of beer on the wall.\n";
      case 1:
        return "1 bottles of beer on the wall, " +
            "1 bottles of beer.\n" +
            "Take it down and pass it around, " +
            "no more bottles of beer on the wall.\n";
      case 2:
        return "2 bottles of beer on the wall, " +
            "2 bottles of beer.\n" +
            "Take one down and pass it around, " +
            "1 bottle of beer on the wall.\n";
      default:
        String template = "%d bottles of beer on the wall, %d bottles of beer.\n"
            + "Take one down and pass it around, %d bottles of beer on the wall.\n";
        return String.format(template, number, number, number - 1);
    }
  }

  public String verses(int i, int i1) {
    return this.verse(99) + "\n" + this.verse(98);
  }
}
