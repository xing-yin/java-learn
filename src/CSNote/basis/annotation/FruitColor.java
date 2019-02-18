package CSNote.basis.annotation;

import java.lang.annotation.*;

/**
 * 水果颜色注解
 *
 * @author yinxing
 * @date 2019/2/14
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色枚举
     */
    public enum Color {
        BLUE, RED, GREEN
    };

    Color fruitColor() default Color.GREEN;
}
