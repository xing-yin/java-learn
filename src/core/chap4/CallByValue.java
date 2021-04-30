package core.chap4;

/**
 * java 总是按值调用:方法得到的是传参值的拷贝
 * <p>
 * - 基本数据类型参数修改不影响原来参数的值
 * - 注意对象引用参数，对象参数拷贝后是对象引用，对其修改会影响对象本身
 *
 * @author Alan Yin
 * @date 2021/4/7
 */

public class CallByValue {

    public static void main(String[] args) {

//        test1();

//        test2();

        test3();

    }

    private static void test3() {
        Employee employee1 = new Employee();
        employee1.setAge(10);
        Employee employee2 = new Employee();
        employee2.setAge(20);
        System.out.printf("employee1 age:%d,employee2 age:%d", employee1.getAge(), employee2.getAge());
        swap(employee1, employee2);
        System.out.println();
        System.out.printf("employee1 age:%d,employee2 age:%d", employee1.getAge(), employee2.getAge());
    }

    private static void swap(Employee a, Employee b) {
        // 不会交换
        Employee tmp = a;
        a = b;
        b = tmp;
    }

    private static void test2() {
        Employee employee = new Employee();
        employee.setAge(10);
        System.out.println(employee.getAge());
        updateAge(employee);
        System.out.println(employee.getAge());
    }

    private static void test1() {
        int a = 1;
        incr(a);
        System.out.println(a);
    }

    private static void updateAge(Employee employee) {
        employee.setAge(20);
    }

    private static void incr(int a) {
        a++;
    }

}
