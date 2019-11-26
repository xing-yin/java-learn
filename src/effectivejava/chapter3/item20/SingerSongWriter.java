package effectivejava.chapter3.item20;

/**
 * 正例:
 * <p>
 * 我们可以定义一个继承歌手和作曲家的第三个接口，并添加适合于这个组合的新方法
 *
 * @author yinxing
 * @date 2019/8/26
 */

public interface SingerSongWriter extends Singer, SongWriter {

    void actSensitive();

    default void method() {
        System.out.println("this is a default method.");
    }
}
