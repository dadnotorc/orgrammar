/*
Easy


 */
package lintcode;

/**
 * 1141 · The month's days
 *
 * Given a year and month, return the days of that month.
 *
 * 1 <= year <= 10000
 * 1 <= month <= 12
 *
 * Example 1:
 * Input:
 * 2020
 * 2
 * Output:
 * 29
 *
 * Example 2:
 * Input:
 * 2020
 * 3
 * Output:
 * 31
 */
public class _1141_TheMonthDays {

    /**
     * 使用数组记录预设值
     */
    public int getTheMonthDays_2(int year, int month) {
        int[] days = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            days[1] = 29;
        }
        return days[month - 1];
    }

    /**
     * 使用 switch statement
     */
    public int getTheMonthDays(int year, int month) {
        int ans = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                ans = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                ans = 30;
                break;
            case 2:
                if (isLeapYear(year)) {
                    ans = 29;
                } else {
                    ans = 28;
                }
        }
        return ans;
    }

    public boolean isLeapYear(int year) {
        return (year % 400 == 0 ||
                (year % 4 == 0 && year % 100 != 0));
    }
}
