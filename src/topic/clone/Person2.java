package topic.clone;

/**
 * Person中有两个成员变量，分别是name和age， name是String类型， age是int类型
 * 由于age是基本数据类型， 那么对它的拷贝没有什么疑议，直接将一个4字节的整数值拷贝过来就行。
 * name是String类型的， 它只是一个引用， 指向一个真正的String对象，那么对它的拷贝有两种方式：浅拷贝、深拷贝
 *
 * @author yinxing
 * @date 2019/8/16
 */

public class Person2 implements Cloneable {

    private int age;

    private String name;

    public Person2() {
    }

    public Person2(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    static class Body implements Cloneable {
        public Head head;

        public Body() {
        }

        public Body(Head head) {
            this.head = head;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Body newBody = (Body) super.clone();
            /**
             * 使Body对象在clone时对 head进行深拷贝
             */
            newBody.head = (Head) head.clone();
            return newBody;
        }
    }

    static class Head implements Cloneable {
        public Face face;

        public Head() {
        }

        public Head(Face face) {
            this.face = face;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class Face implements Cloneable {

    }

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 如果要使Body对象在clone时进行深拷贝， 那么就要在Body的clone方法中，将源对象引用的Head对象也clone一份。
         */
        Person2.Body body1 = new Person2.Body(new Person2.Head());
        Person2.Body body2 = (Person2.Body) body1.clone();
        System.out.println("body1==body2:" + (body1 == body2));
        System.out.println("body1.head==body2.head:" + (body1.head == body2.head));
    }

}
