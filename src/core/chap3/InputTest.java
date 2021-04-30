package core.chap3;

import java.util.Scanner;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class InputTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("what is your name?");
        String name = in.nextLine();
        System.out.println("what is your age?");
        int age = in.nextInt();
        System.out.println("name is:" + name + ";age is:" + age);

        // 敏感信息输入
//        Console console =System.console();
//        String username= console.readLine("username:");
//        char[] password = console.readPassword("password:");
//        System.out.println("username is:" + username + ";password is:" + password[0]);

        System.out.printf("hi,%s,your age is %d", name, age);
    }
}
