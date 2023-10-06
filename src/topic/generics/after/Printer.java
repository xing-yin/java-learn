package topic.generics.after;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/10/6
 **/
public class Printer<T> {

  T content;

  public Printer(T content) {
    this.content = content;
  }

  public void print() {
    System.out.println(this.content);
  }
}
