package effectivejava.chapter1.item7;

import java.util.WeakHashMap;

/**
 * 容易发生内存泄漏情形3: 监听器和其他回调
 *
 * @author yinxing
 * @date 2019/8/14
 */

public class ListenerOrCallback {

    /**
     * 如果你实现了一个 API，其客户端注册回调，但是没有显式地撤销注册回调，除非采取一些操作，否则它们将会累积。
     * 确保回调是垃圾收集的一种方法是 只存储弱引用（weak references） ，例如，仅将它们保存在 WeakHashMap 的键（key）中。
     */
    public WeakHashMap listenerOrCallback = new WeakHashMap();
}
