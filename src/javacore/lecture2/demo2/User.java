package javacore.lecture2.demo2;

/**
 * @author Alan Yin
 * @date 2020/4/2
 */

public class User {

    String name;

    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
