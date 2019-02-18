package CSNote.basis;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class JavaBase {


    public static void main(String[] args) {

        /**
         * valueOf 方法
         */
//        Integer x = new Integer(123);
//        Integer y = new Integer(123);
//        System.out.println(x==y);
//
//        Integer a = Integer.valueOf(123);
//        Integer b = Integer.valueOf(123);
//        System.out.println(a==b);

        /**
         * 编译器会在自动装箱过程调用 valueOf() 方法，
         * 因此多个 Integer 实例使用自动装箱来创建并且值相同，那么就会引用相同的对象
         */
//        Integer m = 123;
//        Integer n = 123;
//        System.out.println(m==n);

        /**
         * String, StringBuffer, StringBuilder
         */
//        // 线程安全
//        StringBuffer stringBuffer = new StringBuffer();
//        // 线程不安全
//        StringBuilder stringBuilder = new StringBuilder();

        /**
         * String Pool
         */
//        String s1 = new String("aaa");
//        String s2 = new String("aaa");
//        System.out.println(s1==s2);  // false
//        String s3 = s1.intern();
//        String s4 = s1.intern();
//        System.out.println(s3==s4); // true


//        String s5 = "bbb";
//        String s6 = "bbb";
//        System.out.println(s5 == s6);   // true

        /**
         * 错误写法：1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。
         * java 不能隐式执行向下转型，因为这会使得精度降低。
         */
//        // float f =1.1;
//        float f = 1.1f;
//
//        short s1 = 1;
//        // 字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。
//        // s1 = s1 + 1;
//
//        // 但是使用 += 或者 ++ 运算符可以执行隐式类型转换。
//        // 相当于 s1 = (short) (s1 + 1);
//        s1 += 1;
//        s1++;

//        String s = "a";
//        switch (s){
//            case "a":
//                System.out.println("aaa");
//                break;
//            case "b":
//                System.out.println("bbb");
//                break;
//            default:
//                System.out.println("other");
//        }


        //        Integer a =null;
        // System.out.println(a.equals(null));


//        int a = 1;
//        int b = 1;
//        System.out.println(a==b);

        Integer x = new Integer(1);
        Integer y = new Integer(1);
        System.out.println(x==y);
        System.out.println(x.equals(y));

    }
}
