package CSNote.basis.exception;

/**
 * @author yinxing
 * @date 2019/2/13
 */

public class MyException extends Exception {

    private int detail;

    public MyException(int detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MyException [" + detail + "]";
    }

}
