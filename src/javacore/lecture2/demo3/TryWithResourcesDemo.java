package javacore.lecture2.demo3;

import java.io.*;

/**
 * Java 便捷特性: try-with- resources 和 multiple catch
 *
 * @author Alan Yin
 * @date 2020/4/2
 */

public class TryWithResourcesDemo {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("filename"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("filename"))) {
            // do something
        } catch (IOException | NullPointerException e) { // Multiple catch
            // Handle it
        }
    }
}
