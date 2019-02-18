package CSNote.basis.object_method;

/**
 * 拷贝对象和原始对象的引用类型引用同一个对象。
 * @author yinxing
 * @date 2019/2/11
 */

public class ShallowCloneExample implements Cloneable {

    private int[] arr;

    public ShallowCloneExample() {
        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected ShallowCloneExample clone() throws CloneNotSupportedException {
        return (ShallowCloneExample) super.clone();
    }

    public static void main(String[] args) {
        ShallowCloneExample s1 = new ShallowCloneExample();
        ShallowCloneExample s2 = null;

        try {
            s2 = s1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        s2.set(2, 2222);
        // 2222
        System.out.println("s2.get(2):  " + s2.get(2));
        // 2222
        System.out.println("s1.get(2):  " + s1.get(2));
    }
}
