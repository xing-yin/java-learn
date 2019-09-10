package effectivejava.chapter6.item50;

import java.util.Date;

/**
 * 使用保护性拷贝
 *
 * @author yinxing
 * @date 2019/9/10
 */

public class Period {

    private final Date start;
    private final Date end;

    /**
     * 反例:没有使用保护性拷贝
     * @param start
     * @param end
     */
//    public Period(Date start, Date end) {
//        if (start.compareTo(end) > 0) {
//            throw new IllegalArgumentException(start + "after" + end);
//        }
//
//        this.start = start;
//        this.end = end;
//    }
//
//    public Date getStart() {
//        return start;
//    }
//
//    public Date getEnd() {
//        return end;
//    }


    /**
     * Repaired constructor - makes defensive copies of parameters
     * 正例:使用保护性拷贝
     * <p>
     * 注意，我们没有使用 Date 的 clone 方法来创建防御性拷贝。因为 Date 是非 final 的，所以 clone 方法不能保证返回类为 java.util.Date 的对象,
     * 它可以返回一个不受信任的子类的实例，这个子类是专门为恶意破坏而设计的。
     * 例如，这样的子类可以在创建时在私有静态列表中记录对每个实例的引用，并允许攻击者访问该列表。这将使攻击者可以自由控制所有实例。
     * 为了防止这类攻击，不要使用 clone 方法对其类型可由不可信任子类化的参数进行防御性拷贝。
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "after" + end);
        }
    }

    public Date getStart() {
        return new Date(start.getTime());
    }

    public Date getEnd() {
        return new Date(end.getTime());
    }

    /**
     * Repaired accessors - make defensive copies of internal fields
     */

    public static void main(String[] args) {
        /**
         * 攻击1:Attack the internals of a Period instance
         *
         * 乍一看，这个类似乎是不可变的，并强制执行不变式，即 period 实例的开始时间并不在结束时间之后。
         * 然而，利用 Date 类是可变的这一事实很容易违反这个不变式
         */
//        Date start = new Date();
//        Date end = new Date();
//        Period p = new Period(start, end);
//        // Modifies internals of p!
//        end.setYear(20);
//        System.out.println(p.getEnd().getYear());

        /**
         * 攻击2: Second attack on the internals of a Period instance
         *
         * 为了抵御第二次攻击，只需修改访问器以返回可变内部字属性的防御性拷贝: 可以防止替代原始数据结构
         */
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        // Modifies internals of p!
        p.getEnd().setYear(20);
        System.out.println(p.getEnd().getYear());
    }
}
