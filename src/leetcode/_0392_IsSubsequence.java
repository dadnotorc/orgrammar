/*
Easy
#Binary Search, #DP, #Greedy
 */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 392. Is Subsequence
 *
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check
 * one by one to see if T has its subsequence. In this scenario, how would you change your code?
 *
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * Constraints:
 * - 0 <= s.length <= 100
 * - 0 <= t.length <= 10^4
 * - Both strings consists only of lowercase characters.
 */
public class _0392_IsSubsequence {

    /**
     * 二分法
     *
     * todo 再读一遍
     */
    public boolean isSubsequence_BS(String s, String t) {
        List<Integer>[] indexes = new List[256]; // 虽然只有26个小写字母, 但是这样后续写起来比较简便, 不用 c-'a'
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (indexes[c] == null) {
                indexes[c] = new ArrayList<>();
            }
            indexes[c].add(i);
        }

        int prev = 0; // prev是t中的指针, 当找到s中对应的字符后, prev后移
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (indexes[c] == null) {
                return false;
            }

            // j 是indexes[c]这个list的下标指针
            // j所指的值, 是当前字符c在t中出现的位置(prev)
            int j = Collections.binarySearch(indexes[c], prev);
            if (j < 0) {
                j = - j -1;
            }
            if (j == indexes[c].size()) { // s中的字符, 在t中已不再出现
                return false;
            }
            prev = indexes[c].get(j) + 1;
        }

        return true;
    }


    /**
     *
     * todo 有bug
     * DP - dp[i][j] 表示s从0到i的子字符串, 是否是t从0到j的子字符串的subsequence
     * 如果s[i] = t[j], 则dp[i][j] = dp[i - 1][j - 1]
     * 否则, dp[i][j] = dp[i - 1][j]
     *
     */
    public boolean isSubsequence_DP(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        for (int j = 0; j < tLen; j++) {
            dp[sLen][j] = true;
        }

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = tLen - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] || dp[i][j + 1];
                } else {
                    dp[i][j] = dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    /**
     * 两根指针分别指向两个字符串中的字符. 当字符相同时, 分别后移; 否则, s的指针不动, 后移t的指针
     * s指针到头, 说明s中的每个字符均在t中按顺序出现;
     * 如果s指针未到头, 但是t指针到头, 说明仍未读完s
     *
     * time: O(n + m) - n为s长度, m为t长度
     */
    public boolean isSubsequence_recursion(String s, String t) {
        return helper(s.toCharArray(), t.toCharArray(), 0, 0);
    }

    private boolean helper(char[] s, char[] t, int i, int j) {
        if (i == s.length) { // s已读完
            return true;
        }
        if (j == t.length) { // s未读完, 但是t已读完
            return false;
        }

        if (s[i] == t[j]) {
            return helper(s, t, i + 1, j + 1);
        }

        return helper(s, t, i, j + 1); // 注意, s指针不动, t指针后移
    }
}
