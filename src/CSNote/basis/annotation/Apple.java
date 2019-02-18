package CSNote.basis.annotation;

/**
 * 注解使用
 *
 * @author yinxing
 * @date 2019/2/14
 */

public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.BLUE)
    private String appleColor;

    @FruitProvider(id=1,name = "红富士",address = "陕西")
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public void displayName(){
        System.out.println("水果的名字:苹果");
    }

    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
    }
}
