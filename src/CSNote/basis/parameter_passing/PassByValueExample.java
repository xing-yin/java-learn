package CSNote.basis.parameter_passing;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class PassByValueExample {

    public static void main(String[] args) {
        Dog dog = new Dog("A");
        System.out.println("dog.getObjectAdress():" + dog.getObjectAdress());   // Dog@2503dbd3
        // 在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中
        func(dog);
        System.out.println("dog.getName():" + dog.getName());                   // A

        // 如果在方法中改变对象的字段值会改变原对象该字段值，因为改变的是同一个地址指向的内容。
        func2(dog);
        System.out.println("dog.getName():" + dog.getName());                   // B
    }

    private static void func(Dog dog) {
        System.out.println("dog.getObjectAdress():" + dog.getObjectAdress());   // Dog@2503dbd3
        dog = new Dog("B");
        System.out.println("dog.getObjectAdress():" + dog.getObjectAdress());   // Dog@4b67cf4d
        System.out.println("dog.getName():" + dog.getName());                   // B
    }

    private static void func2(Dog dog) {
        dog.setName("B");
    }
}
