package javacore.lecture2.demo3;

/**
 * 错误示例:原因分析见 github
 *
 * @author Alan Yin
 * @date 2020/4/2
 */

public class BadDemo {

    void bad1(){
        try {
            // 业务代码
            Thread.sleep(1000);
        } catch (Exception e) {
            // ignore it
        }
    }

    void bad2(){
        try {
            // 业务代码
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
