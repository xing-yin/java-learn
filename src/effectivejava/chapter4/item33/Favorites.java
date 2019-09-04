package effectivejava.chapter4.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yinxing
 * @date 2019/9/3
 */

public class Favorites {

    private Map<Class<?>, Object> favotites = new HashMap<>();

    /**
     * Typesafe heterogeneous container pattern - API
     * 类型安全异类容器模式-API
     * <p>
     * Favorites 类看起来就像一个简单 Map 类，除了该键是参数化的以外。
     * 客户端在设置和获取 favorites 实例时呈现一个 Class 对象。
     */
    public <T> void putFavorite(Class<T> type, T instance) {
        // 方式1:
//        favotites.put(Objects.requireNonNull(type), instance);
        // 方式2:Achieving runtime type safety with a dynamic cast
        // 使用动态强制转换实现运行时类型安全
        favotites.put(Objects.requireNonNull(type),type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favotites.get(type));
    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "java");
        f.putFavorite(Integer.class, 12345);
        f.putFavorite(Class.class, Favorites.class);

        String s = f.getFavorite(String.class);
        int i = f.getFavorite(Integer.class);
        Class<?> clazz = f.getFavorite(Class.class);
        System.out.printf("%s %x %s", s, i, clazz.getName());
    }

}
