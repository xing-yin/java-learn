package topic.exception;

/**
 * @author Alan Yin
 * @date 2021/3/29
 */

public class ExceptionDemo {

    public void checkedException1() throws InterruptedException {
     Thread.sleep(1);
    }

    public void checkedException2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runtimeException(Integer param) {
       if (param == null){
           throw new IllegalArgumentException("param can not be null");
       }
    }
}
