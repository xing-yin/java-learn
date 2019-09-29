package effectivejava.chapter10.item87;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 正例:StringList 的合理序列化形式就是列表中的字符串数量，然后是字符串本身
 * <p>
 * StringList with a reasonable custom serialized form
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class StringList1 implements Serializable {

    /**
     * 在决定使字段非 transient 之前，请确信它的值是对象逻辑状态的一部分。
     * 如果使用自定义序列化表单，大多数或所有实例字段都应该标记为 transient，如示例所示。
     * <p>
     * transient 修饰符表示要从类的默认序列化表单中省略该实例字段
     */
    private transient int size = 0;

    private transient Entry head = null;

    /**
     * 不再序列化
     */
    private static class Entry {
        String data;
        Entry next;
        Entry pervious;
    }

    /**
     * Appends the specified string to the list
     */
    public final void add(String s) {
        // ...
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int numElements = stream.readInt();
        // read in all elements and insert them in list
        for (int i = 0; i < numElements; i++) {
            add((String) stream.readObject());
        }
    }

    /**
     * Serialize this {@code StringList} instance.
     *
     * @serialData The size of the list (the number of strings
     * it contains) is emitted ({@code int}), followed by all of
     * its elements (each a {@code String}), in the proper
     * sequence.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(size);
        // write out all elements in the proper order
        for (Entry e = head; e != null; e = e.next) {
            add(e.data);
        }
    }

    /**
     * writeObject 做的第一件事是调用 defaultWriteObject, readObject 做的第一件事是调用 defaultReadObject。
     * 即使 StringList 的所有字段都是 transient ，这些调用使得在以后版本中添加非瞬态实例字段成为可能，同时保留了向后和向前兼容性。
     */
}
