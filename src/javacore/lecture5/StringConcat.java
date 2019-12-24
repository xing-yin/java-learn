package javacore.lecture5;

/**
 * 在JDK 8中，字符串拼接操作会自动被javac转换为StringBuilder操作
 * <p>
 * 默认缓存大小也在不断地扩大中， 从最初的1009，到7u40以后被修改为60013。你可以使用下面的参数直接打印具体数字，可以拿自己的JDK立刻试验一下。
 * -XX:+PrintStringTableStatisics
 *
 * @author Alan Yin
 * @date 2019/12/24
 */

public class StringConcat {

    public static String concat(String str) {
        return str + "aa" + "bb" + "cc";
    }

    public static void main(String[] args) {
        concat("str:");
    }
}
/**
 * public static java.lang.String concat(java.lang.String);
 * descriptor: (Ljava/lang/String;)Ljava/lang/String;
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=1, args_size=1
 * 0: new           #2                  // class java/lang/StringBuilder
 * 3: dup
 * 4: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
 * 7: aload_0
 * 8: invokevirtual #4                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 11: ldc           #5                  // String aabbcc
 * 13: invokevirtual #4                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 16: invokevirtual #6                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 19: areturn
 * LineNumberTable:
 * line 13: 0
 */