package CSNote.basis.object_method;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class DeepCloneExample implements Cloneable {

    private int[] arr;

    public DeepCloneExample() {
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

    /**
     * 与浅拷贝的不同之处,在于重新引用了一个新的对象
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected DeepCloneExample clone() throws CloneNotSupportedException {
       DeepCloneExample result = (DeepCloneExample)super.clone();
       result.arr = new int[arr.length];
       for(int i =0;i<arr.length;i++){
           result.arr[i] = arr[i];
       }
       return result;
    }


    public static void main(String[] args) {
        DeepCloneExample d1 = new DeepCloneExample();
        DeepCloneExample d2 = null;

        try {
            d2 = d1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        d2.set(2, 2222);
        // 2222
        System.out.println("d2.get(2):  " + d2.get(2));
        // 2
        System.out.println("d1.get(2):  " + d1.get(2));
    }
}
