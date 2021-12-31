package topic.final_test;

/**
 * 演示：final 声明的引用类型和原生类型
 *
 * @author Alan Yin
 * @date 2021/11/30
 */

public class FinalDemo {


    public static void main(String[] args) {
        modifyFinalPrimitive();

        modifyFinalReferType();
    }

    /**
     * 修改 final 修饰的原生类型
     */
    private static void modifyFinalPrimitive() {
        final int i = 6;
        // i = 8; // 不允许修改，因为被 final 修饰
    }

    /**
     * 修改 final 修饰的引用类型
     */
    private static void modifyFinalReferType() {
        final User user = new User("jack", 20);
        System.out.println("before modify:" + user);
        // user = new User("jack", 20); // 不允许修改，因为被 final 修饰
        user.setName("mike");// 允许修改，即使被 final 修饰
        System.out.println("after modify:" + user);
    }

}
