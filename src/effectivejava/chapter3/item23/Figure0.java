package effectivejava.chapter3.item23;

/**
 * 总之，标签类很少有适用的情况。 如果你想写一个带有显式标签字段的类，请考虑标签字段是否可以被删除，并是否能被类层次结构替换。
 * 当遇到一个带有标签字段的现有类时，可以考虑将其重构为一个类层次结构。
 *
 * @author yinxing
 * @date 2019/8/26
 */

public interface Figure0 {

    double area();

}
