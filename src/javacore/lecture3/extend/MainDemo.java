package javacore.lecture3.extend;

/**
 * 测试 finalize
 *
 * @author Alan Yin
 * @date 2020/4/9
 */

public class MainDemo {

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    /**
     * 证明1: 对象的 finalize 方法不一定会被调用，即使是进程退出前。
     * <p>
     * finalize 是在 JVM 执行 GC 的时候才会执行的,而 GC 是否执行以及其执行的时机并不是我们可以精确控制的
     * <p>
     * 输出:
     * A()
     * B()
     */
    public static void test1() {
        A a = new A();
        B b = new B();
        a.b = b;
        a = null;
    }

    /**
     * 证明2: 发生 GC 时一个对象的内存是否释放取决于是否存在该对象的引用，如果该对象包含对象成员，那对象成员也遵循本条
     * <p>
     * 尽管不能精确控制 GC 的时机，但我们可以给 JVM 建议，比如我们在最后加个 System.gc() 建议 JVM 进行 GC
     * <p>
     * 输出:
     * A()
     * B()
     * A finalize
     */
    public static void test2() {
        A a = new A();
        B b = new B();
        a.b = b;
        a = null;
        // 对象的 B 类对象成员由于被局部变量 b 引用，此时不会释放
        System.gc();
    }

    /**
     * 证明3:对象里包含的对象成员按声明顺序进行释放。（先创建后释放）
     */
    public static void test3() {
        A a = new A();
        a.b = new B();
        a.c = new C();
        a = null;
        System.gc();

        // 如果我们互换一下 A 类声明带注释的 line a 与 line b 的位置
//        A a = new A();
//        a.c = new C();
//        a.b = new B();
//        a = null;
//        System.gc();
    }

}
