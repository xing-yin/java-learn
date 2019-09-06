package effectivejava.chapter5.item41;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * 标记注解实例:
 * <p>
 * 标记注解是特殊类型的注解，其中不包含成员。标记注解的唯一目的就是标记声明，因此，这种注解作为注解而存在的理由是充分的。
 * <p>
 * 确定标记注解是否存在的最好方式是使用isAnnotationPresent()方法，该方法是由AnnotatedElement接口定义的。
 *
 * @author yinxing
 * @date 2019/9/6
 */

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {
}

public class Marker {

    /**
     * Annotate a method using a marker.
     * Notice that no ( ) is needed.
     */
    @MyMarker
    public static void myMethod() {
        Marker marker = new Marker();

        try {
            Method m = marker.getClass().getMethod("myMethod");
            // Determine if the annotation is present
            if (m.isAnnotationPresent(MyMarker.class)) {
                System.out.println("myMethod is present");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("myMethod is not found");
        }
    }

    public static void main(String[] args) {
        myMethod();
    }
}
