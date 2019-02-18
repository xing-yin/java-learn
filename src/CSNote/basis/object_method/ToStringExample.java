package CSNote.basis.object_method;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class ToStringExample {

    private int number;

    public ToStringExample(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        ToStringExample example = new ToStringExample(123);
        // toString() @ 后面的数值为散列码的无符号十六进制表示
        System.out.println(example.toString());
    }
}
