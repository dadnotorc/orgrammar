/*
Easy
#String
Microsoft
 */
package lintcode;

/**
 * 1821 · Min Deletions To Obtain String in Right Format
 *
 * We are given a string S of length N consisting only of letters A and/or B. Our goal is to obtain a string
 * in the format A...AB...B. (all letters A occur before all letters B) by deleting some letters from SS.
 * In particular, strings consisting only of letters A or only of letters B fit this format.
 *
 * Write a function that, given a string S, return the minimum number of letters that need to be deleted
 * from S in order to obtain a string in the above format.
 *
 * - N is an integer within the range [1, 100000];
 * - string S consists only of the characters A and/or B.
 *
 * Example 1
 * Input: "BAAABAB"
 * Output: 2
 * Explanation: We can obtain "AAABB" by deleting the first occurrence of 'B' and the last occurrence of 'A'.
 *
 * Example 2
 * Input: "BBABAA"
 * Output: 3
 * Explanation: We can delete all occurrences of 'A' or 'B'.
 *
 * Example 3
 *
 * Input: "AABBBB"
 * Output: 0
 * Explanation: We do not have to delete any letters, because the given string is already in the expected format.
 */
public class _1821_MinDeletionsToObtainStringInRightFormat {


    /**
     * 时间 O(2 * n) ~ O(n)
     * 空间 O(n)
     */
    public int minDeletionsToObtainStringInRightFormat(String s) {
        if (s == null || s.length() < 2) { return 0; }

        char[] chars = s.toCharArray();
        int left_B = 0, right_A = 0;

        // 第一次遍历, 找出字符串中 A 的个数, 这是答案中删除个数的上限
        for (char c : chars) {
            if (c == 'A') {
                right_A++;
            }
        }

        int ans = right_A;

        // 第二次遍历, 记录应当删除的, 左边的B, 和右边的A, 分别的个数. 两者相加, 即当前共应删除的个数, 用于去之前获得的上限比较去较少者
        for (char c : chars) {
            if (c == 'A') {
                right_A--;
            } else {
                left_B++;
            }

            ans = Math.min(ans, left_B + right_A);
        }

        return ans;
    }

}
