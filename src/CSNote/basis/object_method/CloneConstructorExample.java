package CSNote.basis.object_method;

/**
 * clone() 的替代方案
 *
 * @author yinxing
 * @date 2019/2/11
 */

/**
 * 使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。
 * Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象
 */
public class CloneConstructorExample {

    private int[] arr;

    public CloneConstructorExample() {
        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
    }

    /**
     * 关键的构造函数(使用构造函数深拷贝一个对象)
     * @param original
     */
    public CloneConstructorExample(CloneConstructorExample original) {
        arr = new int[original.arr.length];
        for (int i = 0; i < 10; i++) {
            arr[i] = original.arr[i];
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    public static void main(String[] args) {
        CloneConstructorExample c1 = new CloneConstructorExample();
        CloneConstructorExample c2 = new CloneConstructorExample(c1);
        c2.set(2,2222);
        // 2
        System.out.println(c1.get(2));
        // 2222
        System.out.println(c2.get(2));
    }
}
