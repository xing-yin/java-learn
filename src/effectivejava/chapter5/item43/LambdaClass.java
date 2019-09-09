package effectivejava.chapter5.item43;

import java.util.HashMap;
import java.util.Map;

/**
 * 总之，方法引用通常为 lambda 提供一个更简洁的选择。
 * 如果方法引用看起来更简短更清晰，请使用它们；否则，还是坚持 lambda。
 *
 * @author yinxing
 * @date 2019/9/9
 */

public class LambdaClass {

    private void lambda() {
        Map<String, Integer> map = new HashMap<>();
        // 方式1:
        map.merge("key", 1, (count, incre) -> count + incre);
        // 方式2: 从 Java 8 开始，Integer 类（和所有其他包装数字基本类型）提供了一个静态方法总和，和它完全相同
        map.merge("key", 1, Integer::sum);
    }

    // 总结了所有五种方法引用：

    /**
     * 方法引用类型Method Ref  Type |	举例Example             |    Lambda 等式Lambda Equivalent
     Static	                      |	Integer::parseInt      |	str -> Integer.parseInt(str)
     Bound	                      |	Instant.now()::isAfter |	Instant then = Instant.now();t -> then.isAfter(t)
     Unbound	                  |	String::toLowerCase	   |	str -> str.toLowerCase()
     Class                        |	Constructor	TreeMap<K, V>::new | () -> new TreeMap<K, V>
     Array                        |	Constructor	int[]::new	|	len -> new int[len]
     */

}
