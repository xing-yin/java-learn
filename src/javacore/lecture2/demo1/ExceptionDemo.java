package javacore.lecture2.demo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 异常分类
 * @author Alan Yin
 * @date 2020/4/2
 */

public class ExceptionDemo {

    /**
     * 受检查的异常
     * <p>
     * 例如 IOException
     */
    public static void checkedException() {
        try {
            InputStream inputStream = new FileInputStream("filename");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 非受检查的异常==运行时异常
     * <p>
     * 例如 ArrayIndexOutOfBoundsException 就是非受检查的异常
     */
    public static void unCheckedException() {
        int[] arr = new int[]{1, 2, 3};
        for (int i = 0; ; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        checkedException();
        unCheckedException();
    }
}
