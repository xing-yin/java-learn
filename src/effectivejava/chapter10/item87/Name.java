package effectivejava.chapter10.item87;

import java.io.Serializable;

/**
 * 正例：如果对象的物理表示与其逻辑内容相同，则默认的序列化形式可能是合适的。
 * <p>
 * Good candidate for default serialized form
 * 从逻辑上讲，名字由三个字符串组成，分别表示姓、名和中间名。Name 的实例字段精确地反映了这个逻辑内容。
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class Name implements Serializable {

    /**
     * First name. Must be non-null.
     *
     * @serial: @serial 标记的存在告诉 Javadoc 将此文档放在一个特殊的页面上，该页面记录序列化的形式。
     */
    private final String firstName;

    /**
     * Middle name, or null if there is none.
     *
     * @serial
     */
    private final String middleName;

    /**
     * Last name. Must be non-null.
     *
     * @serial
     */
    private final String lastName;


    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}
