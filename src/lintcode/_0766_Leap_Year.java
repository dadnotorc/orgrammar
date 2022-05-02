package lintcode;

/**
 * 766 · Leap Year
 * Easy
 *
 * Determine whether year n is a leap year.return true if n is a leap year.
 *
 * A leap year (also known as an intercalary year or bissextile year) is a calendar year containing one additional day.
 * If a year is divisible by 4 and not divisible by 100 or divisible by 400,it is a leap year. --wikipedia
 *
 * Example 1:
 * Input : n = 2008
 * Output : true
 *
 * Example 2:
 * Input : n = 2018
 * Output : false
 */
public class _0766_Leap_Year {

    /**
     * 普通年能被4整除且不能被100整除的为闰年
     * 世纪年能被400整除的是闰年
     */
    public boolean isLeapYear(int n) {
        if (n < 0) return false;

        return ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0); // 最外层的 () 不能少
    }
}
