package CSNote.basis.object_method;

/**
 * @author yinxing
 * @date 2019/2/11
 */

/**
 * clone() 方法并不是 Cloneable 接口的方法，而是 Object 的一个 protected 方法。
 * Cloneable 接口只是规定，如果一个类没有实现 Cloneable 接口又调用了 clone() 方法，就会抛出 CloneNotSupportedException。
 */
public class CloneExample implements Cloneable{

    private int a;
    private int b;

    @Override
    protected CloneExample clone() throws CloneNotSupportedException {
        return (CloneExample) super.clone();
    }

    public static void main(String[] args) {
        CloneExample c1 = new CloneExample();
        /**
         * clone() 是 Object 的 protected 方法，它不是 public，
         * 一个类不显式去重写 clone()，其它类就不能直接去调用该类实例的 clone() 方法
         */
        // 未'clone()' has protected access in 'java.lang.Object'
        // CloneExample c2 = c1.clone();

        try {
            CloneExample c2 = c1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
