package javacore.lecture1.demo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2.定义类加载器:
 * <p>
 * 自定义加载器，需要继承ClassLoader,并重写里面的 protected Class findClass(String name) 方法。
 *
 * @author Alan Yin
 * @date 2020/4/1
 */

/**
 * 通过上面的程序代码，简单的实现JVM的类加载过程，知道了程序运行的一点流程。但是在编写的时候有如下坑需要注意
 * <p>
 * a.类文件不需要指定包，否则加载的时候我们需要额外的处理，把包中的"."替换成文件系统的路径"/"。
 * b.需要加载的Hello类中的反射调用的方法要用static修饰，这样invoke的时候第一个参数才可以使用null关键字代替，否则需要创建一个对应的类实例。
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 重写父类方法，返回一个Class对象
     * <p>
     * This method should be overridden by class loader implementations
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        // 字节码文件名
        String classFilename = name + ".class";
        File classFile = new File(classFilename);
        if (classFile.exists()) {
            try (FileChannel fileChannel = new FileInputStream(classFile).getChannel()) {
                MappedByteBuffer mappedByteBuffer = fileChannel
                        .map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
                byte[] b = mappedByteBuffer.array();
                clazz = defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class clazz = classLoader.loadClass(args[0]);
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(null, null);
    }

    /**
     * 3.编译需要加载的类文件
     * <p>
     * 类加载的时候加载的是字节码文件，所以需要预先把定义的Hello类编译成字节友文件。
     * javac Hello.java
     */

    /**
     * 4.编译自定义的类加载器并支行程序
     * <p>
     *  编译代码
     * javac MyClassLoader.java
     *  当然我们也可以同时编译我们所有的java源文件
     * javac *.java
     **/

    /**
     * 5.执行成功之后，我们用下面的语句执行代码，测试是否成功，并查看结果
     * <p>
     * java MyClassLoader Hello
     *  运行结果
     * Hello,I am ....
     * <p>
     * 当程序按照预期显示，就证明我自定义类加载器成功了。
     */
}




