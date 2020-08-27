package topic.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 如何保证 SimpleDateFormat 的线程安全
 *
 * @author Alan Yin
 * @date 2020/8/27
 */

public class GoodSimpleDateFormatDemo {

    // 方式1：每次要使用 SimpleDateFormat 时都创建一个局部的 SimpleDateFormat 对象。
    // 局部变量，自然就不存在线程安全的问题了。但如果需要频繁进行调用的话，每次都要创建新的对象，开销太大。

    // 方式2：对 SimpleDateFormat 进行加锁，这样可以确保同一时间只有一个线程可以持有锁，进而解决线程安全的问题。
    // 但是这种方法在多线程竞争激烈的时候会带来效率问题
    private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static synchronized String format1(Date date) {
        return dateFormat1.format(date);
    }

    public static synchronized Date parse1(String dateStr) throws ParseException {
        return dateFormat1.parse(dateStr);
    }

    // 方式3：使用 ThreadLocal
    // 非延迟加载
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String format2(Date date) {
        return local.get().format(date);
    }

    public static Date parse2(String dateStr) throws ParseException {
        return local.get().parse(dateStr);
    }

    // 延迟加载
    private static ThreadLocal<SimpleDateFormat> local2 = new ThreadLocal<SimpleDateFormat>();

    public static synchronized SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat dateFormat = local.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            local.set(dateFormat);
        }
        return dateFormat;
    }

    public static String format3(Date date) {
        return getSimpleDateFormat().format(date);
    }

    public static Date parse3(String dateStr) throws ParseException {
        return getSimpleDateFormat().parse(dateStr);
    }


}
