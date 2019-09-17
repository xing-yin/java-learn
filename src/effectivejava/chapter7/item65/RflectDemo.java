package effectivejava.chapter7.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

/**
 * 这个例子说明了反射的两个缺点。
 * 首先，该示例可以在运行时生成六个不同的异常，如果没有使用反射实例化，所有这些异常都将是编译时错误。
 * （有趣的是，你可以通过传入适当的命令行参数，使程序生成六个异常中的每一个。）
 * 第二个缺点是，根据类的名称生成类的实例需要 25 行冗长的代码，而构造函数调用只需要一行。
 *
 * @author yinxing
 * @date 2019/9/17
 */

public class RflectDemo {

    /**
     * Reflective instantiation with interface access
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<? extends Set<String>> c1 = null;

        // unChecked cast!
        try {
            c1 = (Class<? extends Set<String>>) Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            fatalError("Class not found.");
        }

        // get the constructor
        Constructor<? extends Set<String>> constructor = null;
        try {
            constructor = c1.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fatalError("No parameterless constructor");
        }

        // Instantiate the set
        Set<String> set = null;
        try {
            set = constructor.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("Constructor not accessible");
        } catch (InstantiationException e) {
            fatalError("Class not instantiable");
        } catch (InvocationTargetException e) {
            fatalError("Constructor threw " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Class doesn't implement Set");
        }

        // Exercise the set
        set.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(set);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
