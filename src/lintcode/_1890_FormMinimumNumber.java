/*
Medium
#String
Amazon
 */
package lintcode;

/**
 * 1890. Form Minimum Number
 *
 * Given a pattern str containing only I and D. I for increasing and D for decreasing.
 * Devise an algorithm to print the minimum number following that pattern.
 * Digits from 1-9 and digits can’t repeat.
 *
 * Notice
 * 1<=|str|<=8
 *
 * Example 1:
 * Input: "D"
 * Output:"21"
 * Explanation:2>1
 *
 * Example 2:
 * Input: "II"
 * Output: "123"
 * Explanation:1<2<3
 *
 * Example 3:
 * Input:"DIDI"
 * Output: "21435"
 *
 * Example 4:
 * Input:"DDIDDIID"
 * Output:"321654798"
 */
public class _1890_FormMinimumNumber {


    /**
     * 不统计遇到的'D'的个数, 而是遇到上一个'I'就停止
     *
     * 易错点:
     * 1. 往前寻找上一个'I'的时候, 别忘了判断是否越界
     */
    public String formMinimumNumber_2(String str) {
        int n = str.length();
        char[] nums = new char[n + 1]; // 数字会比字符串多一位

        int index = 1;

        for (int i = 0; i <= n; i++) {
            if (i == n || str.charAt(i) == 'I') {
                for (int j = 0; j <= i; j++) { // 从0到n, 修改'I'位以及之前的'D'位的值, 直到遇到上一个'I'为止
                    nums[i - j] = (char) ('0' + index);
                    index++;
                    if (i - j - 1 >= 0 && str.charAt(i-j-1) == 'I') { // 别忘了判断下一位 i-j-1 是否越界
                        break;
                    }
                }
            }
        }

        return String.valueOf(nums);
    }


    /**
     * 遍历字符串, 统计在'I'之前遇到的'D'的个数. 遇到'I'时, 从当前位向前填写数字
     *
     * 易错点:
     * 1. 输出的数字会比输入的字符串多一位
     * 2. 修改char[]时, 值需要cast, 而且加上'0'  -  (char) ('0' + index);
     * 3. 遇到'I'后, 别忘了reset count
     *
     */
    public String formMinimumNumber(String str) {
        int n = str.length();
        char[] nums = new char[n + 1]; // 数字会比字符串多一位

        int index = 1;
        int count = 0; // # of D's before each I's

        for (int i = 0; i <= n; i++) {
            if (i != n && str.charAt(i) == 'D') {
                count++;
            } else {
                for (int j = 0; j <= count; j++) { // 从0到n, 修改'I'位以及之前的'D'位的值, 直到遇到上一个'I'为止
                    nums[i - j] = (char) ('0' + index);
                    index++;
                }
                count = 0; // 别忘了reset count of D's
            }
        }

        return String.valueOf(nums);
    }

}
