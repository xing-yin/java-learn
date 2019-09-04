package effectivejava.chapter4.item34;

/**
 * @author yinxing
 * @date 2019/9/4
 */

public class PlanetTest {

    public static void main(String[] args) {
        double earthWeight = 1000;
        double mass = earthWeight / PlanetEnum.EARTH.getSurfaceGravity();
        for (PlanetEnum p : PlanetEnum.values()) {
            System.out.printf("weight on %s is %f %n ", p, p.surfaceWeight(mass));
        }
    }
}
