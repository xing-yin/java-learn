package jdk.demo.java.lang;

/**
 * @author yinxing
 * @date 2019/12/11
 */

public class ObjectDemo {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 尽量不要使用
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        Class<? extends Class> clazz = ObjectDemo.class.getClass();
    }
}
