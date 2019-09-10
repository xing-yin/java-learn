package effectivejava.chapter6.item50;

/**
 * @author yinxing
 * @date 2019/9/10
 */

public class ProtectedCloneDemo {

    /**
     * 总之，如果一个类有从它的客户端获取或返回的可变组件，那么这个类必须防御性地拷贝这些组件。
     * 如果拷贝的成本太高，并且类信任它的客户端不会不适当地修改组件，则可以用文档替换防御性拷贝，该文档概述了客户端不得修改受影响组件的责任。
     */
}
