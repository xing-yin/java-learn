package effectivejava.chapter9.item83;

import sun.jvm.hotspot.oops.FieldType;


/**
 * @author yinxing
 * @date 2019/9/29
 */

public class LazyInitilizationDemo {

    /**
     * Lazy initialization of instance field -- synchronized accessor
     */
    private FieldType fieldType;

    private synchronized FieldType getFieldType() {
        if (fieldType == null) {
            fieldType = computeFieldTypeValue();
        }
        return fieldType;
    }

    private FieldType computeFieldTypeValue() {
        // other code
        return null;
    }

}
