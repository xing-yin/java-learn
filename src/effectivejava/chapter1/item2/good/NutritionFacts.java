package effectivejava.chapter1.item2.good;

/**
 * 【正例】builder pattern
 * <p>
 * 当设计类的构造方法或静态工厂的参数超过几个时，Builder 模式是一个不错的选择，特别是如果许多参数是可选的或相同类型的。
 * builder模式客户端代码比使用重叠构造方法模式（telescoping constructors）更容易读写，并且builder模式比 JavaBeans 更安全。
 * <p>
 * 优点：
 * a.结合了重叠构造方法模式的安全性；
 * b.JavaBeans 模式的可读性
 *
 * @author yinxing
 * @date 2019/8/13
 */


/**
 * NutritionFacts 类是不可变的，所有的参数默认值都在一个地方。
 * builder 的 setter 方法返回 builder 本身，这样就可以进行 '链式调用'，从而生成一个流畅的 API。
 */
public class NutritionFacts {

    private final int servingSize;

    private final int servings;

    private final int calories;

    private final int fat;

    private final int sodium;

    private final int carbohydrate;

    public static class Builder {
        // required parameters
        private final int servingSize;

        private final int servings;

        // optional parameters - initialized to default value
        private int calories = 0;

        private int fat = 0;

        private int sodium = 0;

        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            this.calories = val;
            return this;
        }

        public Builder fat(int val) {
            this.fat = val;
            return this;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            this.carbohydrate = val;
            return this;
        }

        /**
         * 构建返回此对象
         *
         * @return
         */
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }


    public static void main(String[] args) {
        NutritionFacts cola = new Builder(200, 10)
                .calories(100)
                .fat(200)
                .sodium(300)
                .carbohydrate(400)
                .build();
    }
}
