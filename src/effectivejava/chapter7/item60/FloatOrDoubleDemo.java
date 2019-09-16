package effectivejava.chapter7.item60;

import java.math.BigDecimal;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class FloatOrDoubleDemo {

    /**
     * 反例:使用 float 表示精确数字
     */
    public static void testFloat() {
        System.out.println(1.00 - 9 * 0.10);
    }

    /**
     * 反例:使用 double 表示精确数字
     * Broken - uses floating point for monetary calculation!
     */
    public static void testDouble() {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println("itemsBought" + itemsBought);
        System.out.println("change:$" + funds);
    }

    /**
     * 正例:使用 int/long/BigDecimal 表示精确数字
     */
    public static void testInt() {
        int funds = 100;
        int itemsBought = 0;
        for (int price = 10; funds >= price; price += 10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println("itemsBought" + itemsBought);
        System.out.println("change:$" + funds);
    }

    public static void testBigDecimal() {
        BigDecimal funds = new BigDecimal(1.00);
        int itemsBought = 0;
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println("itemsBought" + itemsBought);
        System.out.println("change:$" + funds);
    }

    public static void main(String[] args) {
//        testFloat();
//        testDouble();
        testInt();
        testBigDecimal();
    }
}
