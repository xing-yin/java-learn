package topic.clone;

/**
 * Person中有两个成员变量，分别是name和age， name是String类型， age是int类型
 * 由于age是基本数据类型， 那么对它的拷贝没有什么疑议，直接将一个4字节的整数值拷贝过来就行。
 * name是String类型的， 它只是一个引用， 指向一个真正的String对象，那么对它的拷贝有两种方式：浅拷贝、深拷贝
 *
 * @author yinxing
 * @date 2019/8/16
 */

public class Person3 implements Cloneable {

    private int age;

    private String name;

    public Person3() {
    }

    public Person3(int age, String name) {
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
             * 使Body对象在clone时对 head进行深拷贝,需要对原来的 super.clone()进行替换
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
            /**
             * 使Head对象在clone时对 face 进行深拷贝
             */
            Head newHead = (Head) super.clone();
            newHead.face = (Face) this.face.clone();
            return newHead;
        }
    }

    static class Face implements Cloneable {

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 如果要使Body对象在clone时进行深拷贝， 那么就要在Body的clone方法中，将源对象引用的Head对象也clone一份。
         */
        Body body1 = new Body(new Head(new Face()));
        Body body2 = (Body) body1.clone();
        System.out.println("body1==body2:" + (body1 == body2));
        System.out.println("body1.head==body2.head:" + (body1.head == body2.head));
        System.out.println("body1.head==body2.head:" + (body1.head.face == body2.head.face));
    }

}
