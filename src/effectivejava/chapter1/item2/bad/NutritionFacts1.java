package effectivejava.chapter1.item2.bad;

/**
 * 【反例1】坏代码的味道
 * 构造方法的参数很多，有的参数是必选，有的是可选===》简单地使用多个不同参数数量的构造函数
 * <p>
 * 一个食品的例子:包装上营养成分的标签（部分是必须的属性）
 *
 * @author yinxing
 * @date 2019/8/12
 */

public class NutritionFacts1 {

    /**
     * ml:  required
     */
    private final int servingSize;

    /**
     * per container: required
     */
    private final int serving;

    /**
     * per serving(每份食物): optional
     */
    private final int calories;

    /**
     * 脂肪(g/serving) per serving: optional
     */
    private final int fat;

    /**
     * 钠(mg/serving) per serving: optional
     */
    private final int sodium;

    /**
     * 碳水化合物(g/serving) per serving: optional
     */
    private final int carbohydrate;

    public NutritionFacts1(int servingSize, int serving) {
        this(servingSize, serving, 0);
    }

    public NutritionFacts1(int servingSize, int serving, int calories) {
        this(servingSize, serving, 0, 0);
    }

    public NutritionFacts1(int servingSize, int serving, int calories, int fat) {
        this(servingSize, serving, 0, 0, 0);
    }

    public NutritionFacts1(int servingSize, int serving, int calories, int fat, int sodium) {
        this(servingSize, serving, 0, 0, 0, 0);
    }

    public NutritionFacts1(int servingSize, int serving, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.serving = serving;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }


    public static void main(String[] args) {
        NutritionFacts1 cola = new NutritionFacts1(10, 20);
    }

}
