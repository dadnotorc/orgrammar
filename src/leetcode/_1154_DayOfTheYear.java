/*
Easy
 */
package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * 1154. Day of the Year
 * Given a string date representing a Gregorian calendar date formatted as
 * YYYY-MM-DD, return the day number of the year.
 *
 * Example 1:
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 *
 * Example 2:
 * Input: date = "2019-02-10"
 * Output: 41
 *
 * Example 3:
 * Input: date = "2003-03-01"
 * Output: 60
 *
 * Example 4:
 * Input: date = "2004-03-01"
 * Output: 61
 *
 * Constraints:
 * - date.length == 10
 * - date[4] == date[7] == '-', and all other date[i]'s are digits
 * - date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 */
public class _1154_DayOfTheYear {

    /* Method 1 - use calendar rules */
    private static final int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    public int dayOfYear(String dateStr) {
        String[] s = dateStr.split("-");

         // date[0] - 年, date[1] - 月, date[2] - 日
//        int year = Integer.parseInt(s[0]);
//        int month = Integer.parseInt(s[1]);
//        int day = Integer.parseInt(s[2]);
        int[] date = Arrays.stream(s).mapToInt(t -> Integer.parseInt(t)).toArray();

        int ans = 0;

        for (int i = 0; i < date[1] - 1; i++) { // 月份减一,因为最后一个月不完整.之后要加上日
            ans += daysInMonth[i];
        }

        if (isLeapYear(date[0]) && date[1] > 2) { // 注意 后者判断是 > 2, 而不是 >　１
            ans += date[2] + 1;
        } else {
            ans += date[2];
        }

        return ans;
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0)
            return true;
        if (year % 100 == 0)
            return false;
        if (year % 4 == 0)
            return true;
        return false;
    }

    /* Method 2 - use API */
    public int dayOfYear_Calendar(String dateStr) {
        Calendar cal = Calendar.getInstance();
        int[] date = Arrays.stream(dateStr.split("-")).mapToInt(Integer::parseInt).toArray();

        // Months in Calendar object start from 0
        cal.set(date[0], date[1] - 1, date[2]);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    @Test
    public void test1() {
        String date = "2019-01-09";
        assertEquals(9, dayOfYear(date));
        assertEquals(9, dayOfYear_Calendar(date));
    }
    @Test
    public void test2() {
        String date = "2019-02-10";
        assertEquals(41, dayOfYear(date));
        assertEquals(41, dayOfYear_Calendar(date));
    }
    @Test
    public void test3() {
        String date = "2003-03-01";
        assertEquals(60, dayOfYear(date));
        assertEquals(60, dayOfYear_Calendar(date));
    }
    @Test
    public void test4() {
        String date = "2004-03-01";
        assertEquals(61, dayOfYear(date));
        assertEquals(61, dayOfYear_Calendar(date));
    }
    @Test
    public void test5() {
        String date = "2016-02-29";
        assertEquals(60, dayOfYear(date));
        assertEquals(60, dayOfYear_Calendar(date));
    }
}
