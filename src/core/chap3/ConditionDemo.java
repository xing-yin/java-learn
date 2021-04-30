package core.chap3;

/**
 * @author Alan Yin
 * @date 2021/4/5
 */

public class ConditionDemo {

    enum Size {
        SMALL, MIDDLE, BIG
    }

    void test() {
        Size size = Size.SMALL;
        switch (size) {
            case BIG:
                System.out.println("big");
                break;
            case SMALL:
                System.out.println("small");
                break;
            default:
                System.out.println("middle");
                break;
        }
    }
}
