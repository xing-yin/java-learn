package CSNote.basis.object_method;

import java.util.HashSet;

/**
 * 自己实现equal()方法
 *
 * @author yinxing
 * @date 2019/2/11
 */

public class EqualExample {

    private int x;
    private int y;
    private int z;

    public EqualExample(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        // 检查是否为同一个对象的引用
        if (this == obj) {
            return true;
        }

        // 检查是否是同一个类型
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 将 Object 对象进行转型
        EqualExample that = (EqualExample) obj;

        // 判断每个关键域是否相等
        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    public static void main(String[] args) {
        EqualExample e1 = new EqualExample(1, 1, 1);
        EqualExample e2 = new EqualExample(1, 1, 1);
        System.out.println(e1.equals(e2));

        HashSet<EqualExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        // 没有重写hashcode方法时值为2; 重写hashcode方法时值为1(两个对象的散列值时相同的)
        System.out.println("set.size():" + set.size());
    }

}
