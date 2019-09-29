package effectivejava.chapter10.item90;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 序列化代理
 */

public class Period {

    private final Date start;
    private final Date end;

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
     * Serialization proxy for Period class
     * <p>
     * 1.Period 类是如此简单，以致于它的序列化代理有着与类完全相同的字段。
     * 2.接下来，将 writeReplace 方法添加到外部类中。通过序列化代理，这个方法可以被逐字的复制到任何类中。
     */
    private static class SerilizationProxy implements Serializable {

        /**
         * Any number will do
         */
        private static final long serialVersionUID = 234098243823485285L;

        private final Date start;

        private final Date end;

        public SerilizationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        /**
         * readResolve method for Period.SerializationProxy
         *
         * 最后在 SerializationProxy 类中提供一个 readResolve 方法，返回一个逻辑上等价的外围类的实例。
         * 这个方法的出现，导致序列化系统在反序列化时将序列化代理转为外围类的实例。
         */
        private Object readResolve(){
            return new Period(start,end);
        }

    }

    /**
     * writeReplace method for the serialization proxy pattern
     */
    private Object writeReplace() {
        return new SerilizationProxy(this);
    }

    /**
     * 有了 writeReplace 方法之后，序列化系统永远不会产生外围类的序列化实例，但是攻击者有可能伪造企图违反该类约束条件的示例。
     * 为了防止此类攻击，只需要在外围类中添加如下 readObject 方法。
     *
     * @param stream
     * @throws InvalidObjectException
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
