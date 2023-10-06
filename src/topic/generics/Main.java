package topic.generics;

import java.util.List;
import topic.generics.after.Car;
import topic.generics.after.Printer;
import topic.generics.after.PrinterV2;
import topic.generics.after.Vehicle;
import topic.generics.before.IntegerPrinter;
import topic.generics.before.StringPrinter;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/10/6
 **/
public class Main {

  public static void main(String[] args) {
    // 没有使用泛型之前，每新增一种类型都需要扩展新的类
    IntegerPrinter integerPrinter = new IntegerPrinter(123);
    integerPrinter.print();
    StringPrinter stringPrinter = new StringPrinter("aaa");
    stringPrinter.print();

    System.out.println("================use generics================");
    // 使用泛型后
    Printer<Integer> integerPrinter2 = new Printer<>(123);
    integerPrinter2.print();
    Printer<String> stringPrinter2 = new Printer<>("aaa");
    stringPrinter2.print();

    PrinterV2<Car> printerV2 = new PrinterV2<>(new Car());
    printerV2.print();


    print("hello");
    print(123);
    print(123L);
    print(new Car());


  }

  // 演示方法上使用泛型
  private static <T> void print(T content) {
    System.out.println(content);
  }

  // 演示方法上使用多个泛型
  private static <T, K> void print(T content, K content2) {
    System.out.println(content);
    System.out.println(content2);
  }

  // 通配符演示：
  // - 上界通配符，如List<? extends Vehicle>
  // - 下界通配符，如List<? super Vehicle>
//  private static <T> void print(List<? extends Vehicle> content) {
  private static <T> void print(List<? super Vehicle> content) {
    System.out.println(content);
  }
}
