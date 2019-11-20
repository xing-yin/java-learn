package effectivejava.chapter1.item9;

import java.io.*;

/**
 * 9. 使用 try-with-resources 语句替代 try-finally 语句
 *
 * @author yinxing
 * @date 2019/8/15
 */

public class TryClass {

    /**
     * 劣势：
     * 即使 try-finally 能够正确的关闭资源，实际存在一个微妙的缺陷:
     *     例如由于底层物理设备故障造成的 close 失败，这种情况下，第二个异常完全冲掉了第一个异常,
     *     在异常堆栈中没有第一个异常的记录，可能使调试非常复杂---实际上第一个异常才是真正的问题。
     */

    /**
     * 反例1:
     * try-finally no longer the best way to close resource
     */
    public static String firstLineOfFile1(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    private static final int BUFFER_SIZE = 12;

    /**
     * 反例2:
     * try-finally is ugly when use with more than one resource
     */
    public static void copy1(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);

        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buffer = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /**
     * Java 7 引入了 try-with-resources 语句时，所有这些问题一下子都得到了解决:
     * 要使用 try-with-resources ，资源必须实现 AutoCloseable 接口，该接口由一个返回为 void 的 close 组成。
     */

    /**
     * 优势:
     * a.有更好的可读性，提供更好的诊断：
     * (为了保留你想看到的异常，会抑制多个异常，并且异常被打印到堆跟踪中，并标注为被抑制了(可以使用 getSuppressed方法访问)。
     * 比如第一个异常发生，会将第二个异常抑制住，真实打真正的异常。)
     * </p>
     * b.可以在 try-with-resources 语句中添加 catch 子句，就像在常规的 try-finally 语句中一样。
     * 这允许你处理异常，而不会在另一层嵌套中污染代码。
     */

    /**
     * 正例1：
     * try-with-resources - the best way to close resources
     */
    public String firstLineOfFile2(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    /**
     * 正例2：
     * try-with-resources - on multiple resources -short and sweet
     */
    public static void copy2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        }
    }

    /**
     * 可以在 try-with-resources 语句中添加 catch 子句(仅仅为了展示，这样做看上去有点 stupid)
     * try-with-resources with a catch clause
     */
    public static String firstLineOfFile3(String path, String defaultValue) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultValue;
        }
    }

}

/**
 * 总之：
 * 在处理必须关闭的资源时，使用 try-with-resources 语句替代 try-finally 语句。
 * 生成的代码更简洁，更清晰，并且生成的异常更有用。
 * try-with-resources 语句在编写必须关闭资源的代码时会更容易，也不会出错，而使用 try-finally 语句几乎是不可能的。
 */
