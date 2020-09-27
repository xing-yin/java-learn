package effectivejava.chapter1.item2.bad;

/**
 * 【反例2】坏代码的味道
 * 通过 setter 方法来给 JavaBeans 赋值
 *
 * @author yinxing
 * @date 2019/8/12
 */

public class NutritionFacts2 {


    private int servingSize = -1;

    private int serving = -1;

    private int calories = 0;

    private int fat = 0;

    private int sodium = 0;

    private int carbohydrate = 0;

    public NutritionFacts2() {
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        /**
         * 优点: 没有重叠构造方法的缺点，创建实例容易，代码易阅读
         *
         * 缺点:
         *      a.构造方法被分割成了多次调用，所以在构造过程中 JavaBean 可能处于不一致的状态；
         *        （该类没有通过检查构造参数参数的有效性来强制一致性的选项）
         *      b.排除了让类不可变的可能性，需要程序员增加工作保证线程安全
         */
        NutritionFacts2 cola = new NutritionFacts2();
        cola.setServingSize(200);
        cola.setServing(8);
        cola.setCalories(100);
        cola.setFat(35);
        cola.setSodium(35);
        cola.setSodium(20);
    }
}
