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

/**
 * Builder 模式非常适合类层次结构。 使用平行层次的 builder，每个builder嵌套在相应的类中。
 * 抽象类有抽象的 builder；具体的类有具体的 builder。
 * 例如，考虑代表各种比萨饼的根层次结构的抽象类：
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

        /**
         * builder 对构造方法的一个微小的优势是，builder 可以有多个可变参数，因为每个参数都是在它自己的方法中指定的。
         * 或者，builder 可以将传递给多个调用的参数聚合到单个属性中，如下面 addTopping 方法所演示的那样。
         *
         * @param topping
         * @return
         */
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

/**
 * Builder 模式也有缺点。
 * 为了创建对象，首先必须创建它的 builder。虽然创建这个 builder 的成本在实践中不太可能被注意到，但在看中性能的场合下这可能就是一个问题。
 * 而且，builder 模式比伸缩构造方法模式更冗长，因此只有在有足够的参数时才值得使用它，比如四个或更多。
 * 但是请记住，你可能在以后会想要添加更多的参数。
 * 但是，如果你一开始是使用的构造方法或静态工厂，当类演化到参数数量失控的时候再转到Builder模式，过时的构造方法或静态工厂就会面临尴尬的处境。
 * 因此，通常最好从一开始就创建一个 builder。
 * <p>
 * 总而言之，当设计类的构造方法或静态工厂的参数超过几个时，Builder 模式是一个不错的选择，特别是如果许多参数是可选的或相同类型的。
 * builder模式客户端代码比使用伸缩构造方法（telescoping constructors）更容易读写，并且builder模式比 JavaBeans 更安全。
 */
