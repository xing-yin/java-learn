package effectivejava.chapter3.item15.access_level;

/**
 * @author yinxing
 * @date 2019/11/21
 */

public class SubDemo extends ParentDemo {

    @Override
    void testMethod2() {
        super.testMethod2();
    }

    @Override
    protected void testMethod3() {
        super.testMethod3();
    }

    /**
     * 反例1
     * 如果将访问级别改为 default ，编译会报错，子类重写的级别不能低于父类
     * 需要确保子类的实例在父类的实例可用的地方是可用的（Liskov 替换原则）
     */
//    @Override
//    void testMethod3() {
//        super.testMethod3();
//    }


    @Override
    public void testMethod4() {
        super.testMethod4();
    }

    /**
     * 反例2
     * 如果将访问级别改为 protected ，编译会报错，子类重写的级别不能低于父类
     * 需要确保子类的实例在父类的实例可用的地方是可用的（Liskov 替换原则）
     */
//    @Override
//    protected void testMethod4() {
//        super.testMethod4();
//    }
}
