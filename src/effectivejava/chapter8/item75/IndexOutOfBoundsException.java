package effectivejava.chapter8.item75;

/**
 * @author yinxing
 * @date 2019/9/25
 */

public class IndexOutOfBoundsException extends RuntimeException {

    private int lowerBound, upperBound, index;

    public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
        // generate a detail message captures the failure
        super(String.format(
                "Lower bound: %d, Upper bound: %d, Index: %d",
                lowerBound, upperBound, index));
        // Save failure information for programmatic access
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }

}
