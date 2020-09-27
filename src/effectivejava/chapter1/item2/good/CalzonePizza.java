package effectivejava.chapter1.item2.good;

/**
 * 半圆形烤乳酪馅饼
 * <p>
 * 特别之处:允许指定酱汁是否应该在里面或在外面
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class CalzonePizza extends Pizza {

    private final boolean isSauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        /**
         * Default
         */
        private boolean isSauceInside = false;

        public Builder isSauceInside() {
            isSauceInside = true;
            return this;
        }

        @Override
        public CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public CalzonePizza(Builder builder) {
        super(builder);
        isSauceInside = builder.isSauceInside;
    }

}
