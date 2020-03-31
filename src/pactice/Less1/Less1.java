package pactice.Less1;

/**
 * @author Alan Yin
 * @date 2020/2/3
 */

public class Less1 {

    public static int rightShift1(int num,int m){
        return num >> m;
    }

    public static int rightShift2(int num,int m){
        return num >>> m;
    }

    public static void main(String[] args) {
        int a = 53;
        System.out.println(rightShift1(a,1));
        System.out.println(rightShift2(a,1));
    }
}
