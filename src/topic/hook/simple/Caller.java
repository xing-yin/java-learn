package topic.hook.simple;

/**
 * @author Alan Yin
 * @date 2021/4/25
 */

public class Caller {

    public void call(Callback callback) {
        System.out.println("before");
        callback.doSomething();
        System.out.println("after");
    }
}
