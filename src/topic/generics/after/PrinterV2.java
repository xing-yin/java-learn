package topic.generics.after;

/**
 * <p>泛型可以增加约束，比如泛型继承自 Vehicle，只能是 Vehicle 或者 Vehicle 的子类</p>
 *
 * @author yinxing
 * @since 2023/10/6
 **/
public class PrinterV2<T extends Vehicle> {

  T content;

  public PrinterV2(T content) {
    this.content = content;
  }

  public void print() {
    System.out.println(this.content);
  }
}
