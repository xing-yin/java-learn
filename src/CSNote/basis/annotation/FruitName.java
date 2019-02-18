package CSNote.basis.annotation;

import java.lang.annotation.*;

/**
 * 水果名注解
 *
 * @author yinxing
 * @date 2019/2/14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}

