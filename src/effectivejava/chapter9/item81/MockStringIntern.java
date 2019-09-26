package effectivejava.chapter9.item81;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟 String.intern
 *
 * @author yinxing
 * @date 2019/9/26
 */

public class MockStringIntern {

    private static final ConcurrentHashMap<String, String> MAP = new ConcurrentHashMap<>();

    /**
     * Concurrent canonicalizing map atop ConcurrentMap - not optimal
     * Concurrent map 上的并发规范化映射-不是最优的
     */
    public static String intern0(String s) {
        String previousValue = MAP.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }

    /**
     * Concurrent canonicalizing map atop ConcurrentMap - faster!
     */
    public static String intern(String s) {
        String result = MAP.get(s);
        if (result == null) {
            result = MAP.putIfAbsent(s, s);
            if (result == null) {
                result = s;
            }
        }
        return result;
    }

}
