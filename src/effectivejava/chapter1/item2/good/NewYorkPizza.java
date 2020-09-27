package effectivejava.chapter1.item2.good;

/**
 * 标准的纽约风格的披萨
 * <p>
 * 特别之处:有一个所需的尺寸参数
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class NewYorkPizza extends Pizza {

    public enum Size {
        /**
         * size
         */
        SAMLL, MEDIUM, LARGE
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = size;
        }

        @Override
        public NewYorkPizza build() {
            return new NewYorkPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }


    NewYorkPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

}
