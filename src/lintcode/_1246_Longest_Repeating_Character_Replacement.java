package lintcode;

/**
 * 1246. Longest Repeating Character Replacement
 * Medium
 * #Two Pointers, #sliding window
 * Amazon
 *
 * Given a string that consists of only uppercase English letters, you can
 * replace any letter in the string with another letter at most k times.
 * Find the length of a longest substring containing all repeating letters
 * you can get after performing the above operations.
 *
 * Notice
 * - Both the string's length and k will not exceed 10^4.
 *
 * Example1
 * Input: "ABAB", 2
 * Output: 4
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 *
 * Example2
 * Input: "AABABBA", 1
 * Output: 4
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 * leetcode 424
 */
public class _1246_Longest_Repeating_Character_Replacement {

    /**
     * Sliding window - l:左指针 r:右指针 max:在窗口内出现次数最多的字符的个数
     * 窗口要保证substring长度 r - l + 1 <= max + k. 所以窗口连续出现同一字符越多, substring越长
     * 如果不满足上诉条件时, 减少 l 指针所指字符的出现次数, 并移动 l 向右 (此字符离开窗口, 所以其出现次数-1)
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;

        int max = 0, l = 0;
        int[] counts = new int[26]; // 数组只记录 窗口内 字符的出现次数

        for (int r = 0; r < s.length(); r++) {

            counts[s.charAt(r) - 'A']++;
            max = Math.max(max, counts[s.charAt(r) - 'A']);

            if (r - l + 1 > max + k) {
                counts[s.charAt(l) - 'A']--;
                l++;
            }
        }


        // 这里要注意!
        // 不能直接返回 max + k, 如果 s = "AAAA", k = 2, 应该返回 4, 而不是 max + 2
        return Math.min(max + k, s.length());


        /* 也可以 -  return s.length() - l; */

        // 循环做完后, r 指向原字符串末端, l 指向target substring的开始.
        // 所以substring长度 = r - l + 1 = r + 1 -l = s.length() - l
    }







    /**
     * 有 bug, 当s = "AABABBA", k = 1,  exp = 4, actual = 5
     *
     * 要考虑超出窗口是, count 要减少
     */

    public int characterReplacement_bug(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;

        int max = 0;
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'A']++;
            max = Math.max(max, counts[s.charAt(i) - 'A']);
        }

        return Math.min(max + k, s.length());
    }
}
