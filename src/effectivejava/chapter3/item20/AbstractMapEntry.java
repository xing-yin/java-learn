package effectivejava.chapter3.item20;

import java.util.Map;
import java.util.Objects;

/**
 * Skeletal implementation class :框架实现类
 *
 * @author yinxing
 * @date 2019/8/26
 */

public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

    /**
     * Entries in a modifiable map must override this method
     * 可修改的 map 实例中必须重写此方法
     */
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Implements the general contract of Map.Entry.equals
     * 实现map.entry.equals的通用约定
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AbstractMapEntry)) {
            return false;
        }
        Map.Entry<?, ?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(), getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
