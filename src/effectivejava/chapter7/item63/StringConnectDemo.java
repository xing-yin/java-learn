package effectivejava.chapter7.item63;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class StringConnectDemo {

    public static String useStringConnect(){
        String result = "";
        for (Integer i=0;i<100000;i++){
            result+=i.toString();
        }
        return result;
    }

    public static String useStringBuilder(){
        StringBuilder sb = new StringBuilder(1000*1000);
        for (Integer i=0;i<100000;i++){
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        useStringConnect();
        System.out.println(System.currentTimeMillis()-start1);
        long start2 = System.currentTimeMillis();
        useStringBuilder();
        System.out.println(System.currentTimeMillis()-start2);
    }
}
