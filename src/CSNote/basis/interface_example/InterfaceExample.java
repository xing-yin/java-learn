package CSNote.basis.interface_example;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public interface InterfaceExample {

    void func();

    default void func2(){
        System.out.println("fun2");
    }

    int x = 123;
    // int y;               // Variable 'y' might not have been initialized
    public int z = 0;       // Modifier 'public' is redundant for interface fields
    // private int m = 0;   // Modifier 'private' not allowed here
    // protected int n = 0;    // Modifier 'protected' not allowed here

    // private void  func3();  // Modifier 'private' not allowed here
}
