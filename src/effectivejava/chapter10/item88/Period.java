package effectivejava.chapter10.item88;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Date;

/**
 * 正例: 编写 readObject 时保护性拷贝
 *
 * @author yinxing
 * @date 2019/9/29
 */

public class Period {

    private Date start;
    private Date end;

    /**
     * @param start the beginning of the period
     * @param end   the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException     if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }

    /**
     * readObject method with defensive copying and validity checking
     * <p>
     * 注意，保护性拷贝是在有效性检查之前进行的。
     * 我们没有使用 Date 的 clone 方法来执行保护性拷贝机制。这两个细节对于保护 Period 类免受攻击是必要的（详见 50 条）。
     * <p>
     * 同时注意，为了使用 readObject 方法，我们必须要将 start 和 end 字段声明成为非 final。
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        // Defensively copy our mutable components
        // 保护性拷贝可变的组件
        start = new Date(start.getTime());
        end = new Date(end.getTime());
        // check that our invariants are satisfied
        if (start.compareTo(end) < 0) {
            throw new InvalidObjectException(start + " after " + end);
        }
    }

}
