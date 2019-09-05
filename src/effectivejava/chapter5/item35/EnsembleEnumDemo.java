package effectivejava.chapter5.item35;

/**
 * 正例: 永远不要从枚举的序号中得出与它相关的值; 请将其保存在实例属性中
 * <p>
 * 枚举规范对此 ordinal 方法说道：“大多数程序员对这种方法没有用处。 它被设计用于基于枚举的通用数据结构，如 EnumSet 和 EnumMap。“
 * 除非你在编写这样数据结构的代码，否则最好避免使用 ordinal 方法。
 *
 * @author yinxing
 * @date 2019/9/4
 */

public enum EnsembleEnumDemo {

    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    EnsembleEnumDemo(int size) {
        this.numberOfMusicans = size;
    }

    private int numberOfMusicans;

    public int getNumberOfMusicans() {
        return numberOfMusicans;
    }

    public void setNumberOfMusicans(int numberOfMusicans) {
        this.numberOfMusicans = numberOfMusicans;
    }
}
