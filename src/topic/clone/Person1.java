package topic.clone;

/**
 * Person中有两个成员变量，分别是name和age， name是String类型， age是int类型
 * 由于age是基本数据类型， 那么对它的拷贝没有什么疑议，直接将一个4字节的整数值拷贝过来就行。
 * name是String类型的， 它只是一个引用， 指向一个真正的String对象，那么对它的拷贝有两种方式：浅拷贝、深拷贝
 *
 * @author yinxing
 * @date 2019/8/16
 */

public class Person1 implements Cloneable {

    private int age;

    private String name;

    public Person1() {
    }

    public Person1(int age, String name) {
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
            return super.clone();
        }
    }

    static class Head implements Cloneable {
        public Face face;

        public Head() {
        }

        public Head(Face face) {
            this.face = face;
        }
    }

    static class Face implements Cloneable {

    }

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 在Body类中， 组合了一个Face对象。当对Body对象进行clone时， 它组合的Face对象只进行浅拷贝。
         * 因为 body 的 head 变量使用的是默认的 clone(浅拷贝)
         */
        Body body1 = new Body(new Head());
        Body body2 = (Body) body1.clone();
        System.out.println("body1==body2:" + (body1 == body2));
        System.out.println("body1.head==body2.head:" + (body1.head == body2.head));
        /**
         * 如果要使Body对象在clone时进行深拷贝， 那么就要在Body的clone方法中，将源对象引用的Head对象也clone一份。
         * 见 person2
         */
    }

}
