/*
Easy


 */
package lintcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


/**
 * 2241 · Get the millisecond value of the specified date
 *
 * Please write code to get the milliseconds value of the specified date
 * using the relevant methods in the Date class and the SimpleDateFormat class.
 *
 * There is a getMilliSeconds method in the Solution class of this question.
 * The method has a parameter str of type String representing the specified date to be obtained,
 * the method has to calculate how many milliseconds have elapsed from 1970-01-01 to the specified date
 * and return the statistics, the return value is of type long.
 *
 * - Note the format of the date
 *
 * Sample 1
 * If the input parameter is 2021-01-01 then return.
 * 1609459200000
 *
 * Sample 2
 * If the parameter entered is 1970-01-01 then returns.
 * 0
 * Since the methods for getting the millisecond value of a time all start from 1970-01-01,
 * 0 is returned when the input parameter is 1970-01-01.
 *
 * Sample 3
 * If the input parameter is 1969-01-01 returns.
 * -31536000000
 * Same example two, 1969-01-01 did not exceed 1970-01-01 so the return value is a negative number.
 *
 * Challenge
 * You can also use the Calendar class to do this, as follows.
 * First get the Calendar object from getInstance,
 * then reset the time to 1970-01-01 00:00:00 with the clear method,
 * next set the specified time with the set method,
 * and finally get the millisecond value with the getTimeMillis method.
 */
public class _2241_GetMillisecondOfDate {
    /**
     *
     * @param str  :  str represents a time obtained
     * @return  :  return represents the millisecond value to be returned
     * @throws ParseException
     *
     * Date()：使用当前日期和时间来初始化对象
     * Date(long millisec)：该参数是从 1970年 1 月 1 日起的毫秒数
     *
     */
    private static final String DAYONE = "1970-01-01";

    public long getMilliSeconds(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date cur = sdf.parse(str);
        Date pre = sdf.parse(DAYONE);
        long res = cur.getTime() - pre.getTime();

        return res;
    }


    public long getMilliSeconds_Calendar(String str) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.clear();

        String[] date = str.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        cal.set(year, month - 1, day);

        return cal.getTimeInMillis();
    }


}
