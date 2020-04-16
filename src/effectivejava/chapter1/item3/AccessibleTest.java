package effectivejava.chapter1.item3;

/**
 * AccessibleObject.setAccessible() 方法测试：访问 private 修饰的变量/方法
 *
 * @author yinxing
 * @date 2019/8/13
 */

public class AccessibleTest {

    private int id=0;

    public AccessibleTest(int id) {
        this.id = id;
    }

    protected AccessibleTest() {
        System.out.println("construct method");
    }


}
