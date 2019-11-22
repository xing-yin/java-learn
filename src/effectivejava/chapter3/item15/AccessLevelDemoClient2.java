package effectivejava.chapter3.item15;

import effectivejava.chapter3.item15.access_level.AccessLevelDemo1;

/**
 * 访问级别示例2
 *
 * @author yinxing
 * @date 2019/11/21
 */

public class AccessLevelDemoClient2 {

    public static void main(String[] args) {
        AccessLevelDemo1 demo1 = new AccessLevelDemo1();
        System.out.println("====filed");
        // protected 和 default 级别不可访问
//        System.out.println(demo1.var2);
//        System.out.println(demo1.var3);
        System.out.println(demo1.var4);
        System.out.println("====method");
        // protected 和 default 级别不可访问
//        System.out.println(demo1.testDefault());
//        System.out.println(demo1.testProtected());
        System.out.println(demo1.testPublic());
    }
}
