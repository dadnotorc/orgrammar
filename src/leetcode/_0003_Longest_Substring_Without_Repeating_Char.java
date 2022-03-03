package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 *
 * Adobe, Amazon, Apple, Facebook meta, Google, Microsoft, Oracle, SAP, ...
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
public class _0003_Longest_Substring_Without_Repeating_Char {

    /* 面试时确认
    1. 是否是 连续的 substring, 还是 可不连续的 subsequence

    "aaa", "aab", "abb", "abc"
     */

    /**
     * 用数组记录, 每个字符最后出现的位置下标
     */
    public int lengthOfLongestSubstring_array(String s) {
        if(s == null || s.length() == 0) { return 0; }

        int n = s.length();
        int ans = 0, l = 0, r = 0;

        int[] lastPos = new int[256];
        Arrays.fill(lastPos, -1);

        for (; r < n; r++) {
            char c = s.charAt(r);

            if (lastPos[c] != -1) {
                l = Math.max(l, lastPos[c] + 1);
            }

            ans = Math.max(ans, r - l + 1); // 这句别放在 if 里, 不然就麻烦多了
            lastPos[c] = r;
        }

        return ans;
    }


    /**
     * 相同做法, 用 hashmap 记录
     */
    public int lengthOfLongestSubstring_hashmap(String s) {
        if (s == null || s.length() == 0) { return 0; }

        int ans = 0, l = 0, r = 0;
        // <character, its last position>
        HashMap<Character, Integer> map = new HashMap<>();

        while (r < s.length()) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)) + 1); // 从 r 字符的下一位开始
            }
            map.put(s.charAt(r), r);
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }


    /**
     * hashset 正确解法 - 但是较慢
     */
    public int lengthOfLongestSubstring_2(String s) {
        if (s == null || s.length() == 0) { return 0; }

        int ans = 0, l = 0, r = 0;
        HashSet<Character> set = new HashSet<>();

        while (r < s.length()) {
            if (!set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
                ans = Math.max(ans, set.size());
            } else {
                set.remove(s.charAt(l));
                l++;
            }
        }

        return ans;
    }





    /**
     * 有 bug - hashset 无法解决 "dvdf" 这样的问题
     */
    public int lengthOfLongestSubstring_bug(String s) {
        if (s == null || s.length() == 0) { return 0; }

        int ans = 0;
        int l = 0, r = 0;
        HashSet<Character> set = new HashSet<>();

        while (r < s.length()) {
            if (!set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
            } else {
                ans = Math.max(r - l, ans);
                set.clear();
                l = r;
                set.add(s.charAt(r));
            }

            r++;
        }

        //return ans == 0 ? s.length() : ans; 不能这么写, 不然 "aab" 会错

        ans = Math.max(r - l, ans);

        return ans;
    }


    @Test
    public void test1() {
        String s = "cdd";
        org.junit.Assert.assertEquals(1, lengthOfLongestSubstring_array(s));
    }
}
