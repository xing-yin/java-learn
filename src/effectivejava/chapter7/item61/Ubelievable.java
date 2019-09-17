package effectivejava.chapter7.item61;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class Ubelievable {

//    static Integer i;
//
//    public static void main(String[] args) {
//        if (i == 20) {
//            System.out.println("Ubelievable");
//        }
//    }

    // Hideously slow program! Can you spot the object creation?
    // 程序太慢了！你能看到对象的产生吗？
    public static void main(String[] args) {
        // 反例：误用包装类，自动拆箱装箱，性能下降
        //Long sum = 0L;
        // 正例:使用基本类型
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("sum=" + sum);
        System.out.println("time=" + time);
    }
}
