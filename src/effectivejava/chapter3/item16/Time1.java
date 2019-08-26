package effectivejava.chapter3.item16;

/**
 * 反例: 虽然公共类直接暴露属性并不是一个好主意，但是如果属性是不可变的，那么危害就不那么大了。
 * 当一个属性是只读的时候，除了更改类的 API 外，你不能改变类的内部表示形式，也不能采取一些辅助的行为，但是可以加强不变性。
 *
 * @author yinxing
 * @date 2019/8/16
 */

/**
 * Public class with exposed immutable fields - questionable(值得怀疑的)
 */
public class Time1 {

    private static final int HOURS_PER_DAY = 24;

    private static final int MINUTES_PER_HOUR = 60;

    /**
     * 虽然暴露的是只读属性，危害较小，但依然不建议
     */
    public final int hour;

    public final int minutes;

    public Time1(int hour, int minutes) {
        if (hour < 0 || hour >= HOURS_PER_DAY) {
            throw new IllegalArgumentException("Hour: " + hour);
        }
        if (minutes < 0 || minutes >= MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("Min: " + minutes);
        }
        this.hour = hour;
        this.minutes = minutes;
    }

    // other code

}
