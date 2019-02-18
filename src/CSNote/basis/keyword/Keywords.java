package CSNote.basis.keyword;

/**
 * @author yinxing
 * @date 2019/2/12
 */

public class Keywords {

    public static class A {

        static {
            System.out.println("123");
        }

        // 实例变量
        private int a;
        // 静态变量
        private static int y;

        public static void func() {
            int m = y;
            //int n = a;            // Non-static field 'y' cannot be referenced from a static context
            //int n2 = this.a;     // 'A.this' cannot be referenced from a static context
        }
    }

    private void finalMethod() {
    }

    public static void func1() {
    }

    // 静态方法必须要有实现，不能是抽象方法
    //public abstract static void func2(){};


    /**
     * 静态内部类
     */
    class InnerClass {
    };

    static class StaticInnerClass {
    };

    /**
     * 初始化顺序:
     * 父类(静态变量、静态代码块)
     * 子类(静态变量、静态代码块)
     * 父类(实例变量、普通语句块)
     * 父类(构造函数)
     * 子类(实例变量、普通语句块)
     * 子类(构造函数)
     */


    public static void main(String[] args) throws ClassNotFoundException {
        final int x = 1;
        // x=2; // cannot assign value to final variable 'x'
        final A y = new A();
        y.a = 1;
        A.y = 2;

        // 非静态内部类依赖于外部类的实例，而静态内部类不需要。
        // InnerClass innerClass1 = new InnerClass();  // 'OuterClass.this' cannot be referenced from a static context
        Keywords keywords = new Keywords();
        InnerClass innerClass = keywords.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();

        Class Object = Class.forName("Keywords");

    }
}
