package effectivejava.chapter3.item15.access_level;

/**
 * 访问级别示例0
 *
 * @author yinxing
 * @date 2019/11/21
 */

public class AccessLevelDemo1 {

    private String var1 = "private";

    String var2 = "default";

    protected String var3 = "protected";

    public String var4 = "public";


    private String testPrivate() {
        return "private";
    }

    String testDefault() {
        return "private";
    }

    protected String testProtected() {
        return "protected";
    }

    public String testPublic() {
        return "public";
    }
}
