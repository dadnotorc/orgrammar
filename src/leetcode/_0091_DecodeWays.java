/*
Medium
#String, #DP
Facebook
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 91. Decode Ways
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class _0091_DecodeWays {

    /**
     * DP - top down
     *
     * 注意特判!
     * 1. s = "0" 或者 "000"
     * 2. s = "01"
     * 3. s = "1"
     * 4. s = "100"
     * 5. s = "010"
     *
     * 易错处
     * 1. 判断comb的值时, 注意前一位数字可能为0, 则comb对应字符串可能例如"02", 所以需要加入comb > 9的判断, 排除此可能
     *    另外就是comb要 < 27, 不能超过 Z 对应的值
     */
    public int numDecodings(String s) {
        if (s == null || s.length() < 1) { // 不在这里判断字符串是否值为0, 因为之后会做判断
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // 表示empty string有一种方式decode
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // 对dp[1]要注意, 不能简单的将其赋值为1, 因为字符串可能为 "01"

        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            int cur = chars[i] - '0'; //当前位数字
            int pre = chars[i - 1] - '0'; //前一位数字
            int comb = pre * 10 + cur; // 前一位+当前位 两位数

            if (cur != 0) {
                dp[i + 1] += dp[i]; // 注意 这里用 = 而不是 +1
            }
            if (comb > 9 && comb < 27) { // 注意这里加入 > 9的判断, 避免前一位为0时, 例如comb对应string "02"
                dp[i + 1] += dp[i - 1];
            }
        }

        return dp[chars.length];
    }


    /**
     * 简化第一种写法, 缩小array的长度
     *
     * 注意几点不同:
     * 1. 每次循环中, 必须重置 dp[2] = 0, 否则会错误的累计加和
     * 2. 返回时, 返回dp[1] 而不是 dp[2]:
     *    - 因为字符串长度等于1时, 不会进入循环, dp[2]仍为0
     *    - 因为字符串长度大于1时, 循环末尾会用dp[2]赋值dp[1], 所以dp[1]仍为正确答案
     */
    public int numDecodings_2(String s) {
        if (s == null || s.length() < 1) { // 不在这里判断字符串是否值为0, 因为之后会做判断
            return 0;
        }

        int[] dp = new int[3];
        dp[0] = 1; // 表示empty string有一种方式decode
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // 对dp[1]要注意, 不能简单的将其赋值为1, 因为字符串可能为 "01"

        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            int cur = chars[i] - '0'; //当前位数字
            int pre = chars[i - 1] - '0'; //前一位数字
            int comb = pre * 10 + cur; // 前一位+当前位 两位数

            dp[2] = 0;
            if (cur != 0) {
                dp[2] += dp[1];
            }
            if (comb > 9 && comb < 27) { // 注意这里加入 > 9的判断, 避免前一位为0时, 例如comb对应string "02"
                dp[2] += dp[0];
            }

            dp[0] = dp[1];
            dp[1] = dp[2];
        }

        return dp[1];
    }


    // todo 改变题目要求, 返回具体decoded字符串. 例如 input = "226", output = {"BZ","VF","BBF"}
    // https://www.glassdoor.com/Interview/Given-the-alphabet-encoded-as-numbers-e-g-a-1-b-2-z-26-and-a-sequence-of-numbers-e-g-23413259802-how-man-QTN_1992899.htm
    char[] map = {
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'};

    public List<String> getDecodings(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        // todo
        return ans;
    }

    private void helper(char[] chars, int index, StringBuilder sb, List<String> ans) {
        if (index == chars.length) {
            ans.add(sb.toString());
            return;
        }

        int cur = chars[index] - '0'; //当前位数字
        int pre = chars[index - 1] - '0'; //前一位数字
        int comb = pre * 10 + cur; // 前一位+当前位 两位数
    }
}
