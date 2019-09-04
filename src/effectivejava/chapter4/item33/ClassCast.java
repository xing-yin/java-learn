package effectivejava.chapter4.item33;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Class 类提供了一种安全（动态）执行这种类型转换的实例方法。
 * 该方法被称为 asSubclass，并且它转换所调用的 Class 对象来表示由其参数表示的类的子类。
 * 如果转换成功，该方法返回它的参数；如果失败，则抛出 ClassCastException 异常。
 *
 * @author yinxing
 * @date 2019/9/3
 */

public class ClassCast {

    /**
     * Use of asSubclass to safely cast to a bounded type token
     * 使用 asSubclass 安全地强制转换为有界类型令牌
     * <p>
     * 以下是如何使用 asSubclass 方法在编译时读取类型未知的注解
     */
    static Annotation getAnnotation(AnnotatedElement element,
                                    String annotationTypeName) {
        // unbounded type token
        Class<?> annotationType = null;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
}
