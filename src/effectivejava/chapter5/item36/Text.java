package effectivejava.chapter5.item36;

/**
 * 反例:Bit field enumeration constants - OBSOLETE!
 * 位属性枚举常量-已过时
 * <p>
 * 缺点:
 * a.但是位属性具有 int 枚举常量等的所有缺点。 当打印为数字时，解释位属性比简单的 int 枚举常量更难理解。
 * b.没有简单的方法遍历所有由位属性表示的元素。
 * c.最后，必须预测在编写 API 时需要的最大位数，并相应地为位属性（通常为 int 或 long）选择一种类型。
 * 一旦你选择了一个类型，你就不能超过它的宽度（32 或 64 位）而不改变 API。
 *
 * @author yinxing
 * @date 2019/9/4
 */

public class Text {

    // 1
    public static final int STYLE_BOLD = 1 << 0;
    // 2
    public static final int STYLE_ITALIC = 1 << 1;
    // 4
    public static final int STYLE_UNDERLINE = 1 << 2;
    // 8
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    /**
     * Parameter is bitwise OR of zero or more STYLE_ constants
     * 参数为零个或多个 Style_constants 按位或
     *
     * @param style
     */
    public void applyStyles(int style) {
        // ...
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
}
