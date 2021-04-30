package core.chap4;

import java.time.LocalDate;

/**
 * @author Alan Yin
 * @date 2021/4/6
 */

public class DateTest {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());

        LocalDate newDate = LocalDate.of(1900, 1, 1);
        int year = newDate.getYear();
        int month = newDate.getMonthValue();
        int day = newDate.getDayOfMonth();
        System.out.printf("%d,%d,%d", year, month, day);
        newDate.plusYears(100);
        System.out.printf("%d,%d,%d", year, month, day);

    }
}
