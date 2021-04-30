package topic.hook.event_callback;

/**
 * @author Alan Yin
 * @date 2021/4/25
 */

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        Caller caller = new Caller();
        caller.call(()-> System.out.println("click doing"));
    }
}
