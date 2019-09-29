package effectivejava.chapter10.item87;

import java.io.Serializable;

/**
 * 反例:物理表示与逻辑数据内容有很大差异
 * <p>
 * Awful candidate for default serialized form
 *
 * @author yinxing
 * @date 2019/9/29
 */

/**
 * 从逻辑上讲，这个类表示字符串序列。在物理上，它将序列表示为双向链表。
 * 如果接受默认的序列化形式，该序列化形式将镜像出链表中的所有项，以及这些项之间的所有双向链接。
 */
public class StringList implements Serializable {

    private int size = 0;

    private Entry head = null;

    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry pervious;
    }
}
