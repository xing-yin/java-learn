package effectivejava.chapter10.item89;

import java.util.Arrays;

/**
 * @author yinxing
 * @date 2019/9/29
 */

public enum Elvis1 {

    /**
     * single
     */
    INSTANCE;

    private String[] favorieSongs = {"Hound Dog", "Heart"};

    public void printFavorites() {
        System.out.println(Arrays.toString(favorieSongs));
    }

}
