/*
Naive
#String
 */
package lintcode;

/**
 * 241 · String to Integer
 *
 * Given a string, convert it to an integer. You may assume the string is a valid integer number that
 * can be presented by a signed 32bit integer (-2^31 ~ 2^31-1).
 *
 * Example 1:
 * 	Input:  "123"
 * 	Output: 123
 *
 * Example 2:
 * 	Input:  "2"
 * 	Output: 2
 */
public class _0241_StringToInteger {

    /**
     * 使用数组
     */
    public int stringToInteger(String target) {
        char[] array = target.toCharArray();
        boolean isNegative = (array[0] == '-');
        int start = isNegative ? 1 : 0;
        int ans = 0, digit = 1;

        /* 1234
        1. ans + 4 + 1
        2. ans + 3 * 10
        3. ans + 2 * 100
        4. ans + 1 * 1000
         */
        for (int i = array.length - 1; i >= start; i--){
            ans += (array[i] - '0') * digit; // 这里的逻辑要搞清
            digit *= 10;
        }

        return isNegative ? -ans : ans;
    }

    /**
     * API
     */
    public int stringToInteger_api(String target) {
        return Integer.parseInt(target);
    }
}
