package effectivejava.chapter2.item14;

/**
 * @author yinxing
 * @date 2019/8/22
 */

public class ComparableDemo {

    /**
     * 总而言之，无论何时实现具有合理排序的值类，你都应该让该类实现 Comparable 接口，以便在基于比较的集合中轻松对其实例进行排序，搜索和使用。
     * 比较 compareTo 方法的实现中的字段值时，请避免使用「<」和「>」运算符。
     * 相反，使用包装类中的静态 compare 方法或 Comparator 接口中的构建方法。
     */
}
