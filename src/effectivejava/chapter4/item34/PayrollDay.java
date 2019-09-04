package effectivejava.chapter4.item34;

/**
 * 策略枚举模式
 *
 * @author yinxing
 * @date 2019/9/4
 */

/**
 * 考虑一个代表工资包中的工作天数的枚举。
 * 该枚举有一个方法，根据工人的基本工资（每小时）和当天工作的分钟数计算当天工人的工资。
 * 在五个工作日内，任何超过正常工作时间的工作都会产生加班费; 在两个周末的日子里，所有工作都会产生加班费。
 */
public enum PayrollDay {

    /**
     * Day
     */
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        // default
        this(PayType.WEEKDAY);
    }

    private enum PayType {
        /**
         * type
         */
        WEEKDAY {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked >= MIN_PER_SHIFT ? 0 :
                        (minsWorked - MIN_PER_SHIFT) * payRate * 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MIN_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
