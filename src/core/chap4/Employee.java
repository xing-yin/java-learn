package core.chap4;

import java.util.Date;

/**
 * @author Alan Yin
 * @date 2021/4/7
 */

public class Employee {

    // -------------------------------不要编写返回引用可变对象的访问器方法-------------------------------
    private Date hireDay;

//    public Date getHireDay() {
//        return hireDay; // bad
//    }

    public Date getHireDay() {
        return (Date) hireDay.clone(); // good
    }

    // -------------------------------final-------------------------------
    // final 表示存储在 stringBuilder 变量中的对象不会再指示其他 StringBuilder 对象
    private final StringBuilder stringBuilder;

    public Employee() {
        this.stringBuilder = new StringBuilder();
    }

    // -------------------------------静态域-------------------------------
    /**
     * 静态域属于类，不属于任何对象（有 100 个 Employee对象，只有一个nextId 为1 ）
     */
    private static int nextId = 1;

    // -------------------------------实例域------------------------------
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
