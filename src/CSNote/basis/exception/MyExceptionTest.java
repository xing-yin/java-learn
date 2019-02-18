package CSNote.basis.exception;

/**
 * 自定义异常
 *
 * @author yinxing
 * @date 2019/2/13
 */

public class MyExceptionTest {

    static void compute(int a) throws MyException {
        System.out.println("called compute(" + a + ")");
        if (a > 10) {
            throw new MyException(a);
        }
        System.out.println("Nomal exit!");
    }

    public static void main(String[] args) {
        try {
            compute(1);
            compute(20);
        } catch (MyException e) {
            System.out.println("caught " + e);
        }
    }
}
