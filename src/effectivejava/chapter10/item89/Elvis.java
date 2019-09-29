package effectivejava.chapter10.item89;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 反例: 使用单利
 * ⚠任何一个 readObject 方法，不管是显式的还是默认的，都会返回一个新建的实例，这个新建的实例不同于类初始化时创建的实例。
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class Elvis implements Serializable {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    /**
     * readResolve for instance control - you can do better!
     */
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator.
        return INSTANCE;
    }

    private String[] favorieSongs = {"Hound Dog", "Heart"};

    public void printFavorites() {
        System.out.println(Arrays.toString(favorieSongs));
    }

    /**
     * 如果单例包含一个非 transient 的对象引用字段，这个字段的内容就可以在单例的 readResolve 方法之前被反序列化。
     * 当对象引用字段的内容被反序列化时，它就允许一个精心制作的流「盗用」指向最初被反序列化的单例对象引用。
     * <p></p>
     * 盗用者 的 readResolve 方法从它的实例字段中将引用复制到静态字段中，以便该引用可以在 readResolve 方法运行之后被访问到。
     * 然后这个方法为它所藏身的那个域返回一个正确的类型值。
     * 如果没有这么做，当序列化系统试着将「盗用者」引用保存到这个字段时，虚拟机就会抛出 ClassCastException。
     *
     */

}
