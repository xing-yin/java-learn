package effectivejava.chapter2.item13;

/**
 * @author yinxing
 * @date 2019/8/21
 */

public class NonClone {

    /**
     * 禁止子类实现 clone 方法，这样避免子类重写
     */
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * 方式1：复制功能最好由构造方法提供
     * copy constructor
     */
    public NonClone(NonClone nonClone){
        // ...
    }

    /**
     * 方式2：复制功能最好由工厂提供
     */
    public static NonClone newInstance(NonClone nonClone) {
        // ...
        return null;
    }
}
