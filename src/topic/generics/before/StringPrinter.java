package topic.generics.before;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/10/6
 **/
public class StringPrinter {

  String content;

  public StringPrinter(String content) {
    this.content = content;
  }

  public void print() {
    System.out.println(this.content);
  }
}
