package topic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yinxing
 * @date 2019/9/6
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Interface {

    String value();
}
