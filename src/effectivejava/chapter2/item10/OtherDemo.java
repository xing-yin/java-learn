package effectivejava.chapter2.item10;

/**
 * @author yinxing
 * @date 2019/8/19
 */

public class OtherDemo {

    /**
     * 如果类是私有的或包级私有的，可以确定它的 equals 方法永远不会被调用。
     * 如果你非常厌恶风险，可以重写 equals 方法，以确保不会被意外调用：
     */
    @Override
    public boolean equals(Object obj) {
        // method never call
        throw new AssertionError("can not call equals method");
    }
}
