package effectivejava.chapter1.item2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 请注意，每个子类 builder 中的 build 方法被声明为返回正确的子类：
 * NyPizza.Builder 的 build 方法返回 NyPizza; Calzone.Builder 中的 build 方法返回 Calzone。
 *
 * @author yinxing
 * @date 2019/8/13
 */

public abstract class Pizza {

    public enum Topping {
        /**
         * 火腿、蘑菇、洋葱、胡椒、香肠
         */
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        /**
         * subclass must override this method to return "this"
         *
         * @return
         */
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        // see item 50
        toppings = builder.toppings.clone();
    }


    public static void main(String[] args) {
        NewYorkPizza newYorkPizza = new NewYorkPizza.Builder(NewYorkPizza.Size.SAMLL)
                .addTopping(Topping.HAM).addTopping(Topping.SAUSAGE).build();

        CalzonePizza calzonePizza = new CalzonePizza.Builder()
                .addTopping(Topping.HAM).isSauceInside().build();
    }
}
