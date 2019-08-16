package topic.clone;

/**
 * @author yinxing
 * @date 2019/8/16
 */

public class CloneTest {

    public static void main(String[] args) throws Exception {

//        /**
//         *  情况1:打印的地址相同，p12 是 p11 的一个引用
//         */
//        Person p11 = new Person(20,"jack");
//        Person p12 = p11;
//        // true
//        System.out.println(p11);
//        System.out.println(p12);
//        System.out.println(p11==p12);

//        /**
//         *  情况2:打印的地址相同，p2 是 p1 的一个引用
//         */
//        Person p21 = new Person(20, "jack");
//        Person p22 = (Person) p21.clone();
//        System.out.println(p21);
//        System.out.println(p22);
//        System.out.println(p21 == p22);


        /**
         *  情况3:
         */
        Person p31 = new Person(20, "jack");
        Person p32 = (Person) p31.clone();
        String result = p31.getName() == p32.getName() ? "clone是浅拷贝" : "clone是深拷贝";
        System.out.println(result);


    }
}
