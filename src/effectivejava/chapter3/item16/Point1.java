package effectivejava.chapter3.item16;

/**
 * @author yinxing
 * @date 2019/8/16
 */

/**
 * 坏代码的味道:
 * 退化类: 除了集中实例域，没有说明作用，属性不应该是 public
 */
// Degenerate classes like this should not be public!
class Point1 {

    /**
     * 这些类的数据属性可以直接被访问，因此这些类不提供封装的好处
     */
    public double x;

    public double y;

}
