package pactice;

import java.io.*;

/**
 * @author yinxing
 * @date 2019/11/20
 */

public class TryWithResource {

    public static void test(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buffer = new byte[120];
            int n;
            System.out.println(in.read());
            while ((n = in.read()) > 0) {
                out.write(buffer, 0, n);
            }
            System.out.println(in.read());
        }
    }

    public static void main(String[] args) throws IOException {
        test("you_path/in.txt", "you_path/out.txt");
    }
}
