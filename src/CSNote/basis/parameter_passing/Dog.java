package CSNote.basis.parameter_passing;

/**
 * @author yinxing
 * @date 2019/2/11
 */

public class Dog {

    String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectAdress(){
        return super.toString();
    }

}
