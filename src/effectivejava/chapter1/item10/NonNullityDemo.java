package effectivejava.chapter1.item10;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public class NonNullityDemo {

    @Override
    public boolean equals(Object o) {
        /**
         * 这一步是多余的：
         * 为了测试它的参数是否相等，equals 方法必须首先将其参数转换为合适类型，以便调用访问器或允许访问的属性。
         * 在执行类型转换之前，该方法必须使用 instanceof 运算符来检查其参数是否是正确的类型：如果操作数 o 为 null，则指定 instanceof 运算符返回 false
         */
//        if (o == null) {
//            return false;
//        }
        if(!(o instanceof NonNullityDemo)){
            return false;
        }
        // ...
        return false;
    }
}
