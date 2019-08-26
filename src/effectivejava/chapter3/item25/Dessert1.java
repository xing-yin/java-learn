package effectivejava.chapter3.item25;

/**
 * @author yinxing
 * @date 2019/8/26
 */

public class Dessert1 {

    /**
     * Two classes defined in one file. Don't ever do this!
     * 在一个文件中定义了2个类。不要这样做
     */
    class Utensil {
        static final String NAME = "pot";
    }

    class Dessert {
        static final String NAME = "pie";
    }

}
