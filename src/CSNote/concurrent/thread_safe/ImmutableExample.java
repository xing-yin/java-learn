package CSNote.concurrent.thread_safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 对于集合类型，可以使用 Collections.unmodifiableXXX() 方法来获取一个不可变的集合。
 *
 * @author yinxing
 * @date 2019/3/4
 */

public class ImmutableExample {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        Map<String,Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        // Collections.unmodifiableXXX() 先对原始的集合进行拷贝，需要对集合进行修改的方法都直接抛出异常。
        unmodifiableMap.put("b",2);
    }
}
