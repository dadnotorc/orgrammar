/*
Medium
#String, #DP
Amazon
 */
package leetcode;

/**
 * 926. Flip String to Monotone Increasing
 *
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
 * followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 * Example 1:
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 *
 * Example 2:
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 *
 * Example 3:
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 */
public class _0926_FlipStringToMonotoneIncreasing {

    /**
     * go left to right, and virtually 'move' the split point every time we see that
     * we need less '0' -> '1' than '1' -> '0' flips   (f1 = min(f0, f1 + 1 - (c - '0'))).
     * todo 再读读
     */
    public int minFlipsMonoIncr_DP_2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int f0 = 0, f1 = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            f0 += c - '0'; // 是 '0' 就 +0, 否则 +1

            f1 = Math.min(f0, f1 + 1 - (c - '0'));
        }

        return f1;
    }

    /**
     * 题目的意思是, split string into left '0' and right '1', 而且 '1'->'0' flip (left) 与 '0'->'1' flip (right) 最少
     * 1. Count of '1' -> '0' flips going left to right, and store it in f0.
     *    Count of '0' -> '1' flips going right to left, and store it in f1.
     * 2. Find the smallest f0[i] + f1[i].
     */
    public int minFlipsMonoIncr_DP(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int n = s.length();

        int[] f0 = new int[n + 1];
        int[] f1 = new int[n + 1];

        for (int i = 1, j = n - 1; j >= 0; i++, j--) {
            f0[i] = f0[i - 1] + (s.charAt(i - 1) == '0' ? 0 : 1);
            f1[j] = f1[j + 1] + (s.charAt(j) == '1' ? 0 : 1);
        }

        for (int k = 0; k <= n; k++) {
            ans = Math.min(ans, f0[k] + f1[k]);
        }

        return ans;
    }



    /**
     * prefix-suffix 解法
     * 1. 跳过开头的 0, 直到遇到第一个 1
     * 2. 记录 1 的出现次数 - prefix_1
     * 3. 遇到 1 以后, 之后遇到的所有 0 也是 potential candidate, 要记录出现次数 - suffix_0
     * 4. 遍历完, 比较 prefix_1 与 suffix_0, 并返回较少者
     *
     * 把 string 转成 char array 会使用 O(n) 空间. 解决方法是使用 String.charAt(int index)
     */
    public int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 可 flip 的 1 与 0 的个数
        int prefix_1 = 0, suffix_0 = 0;

        int index = 0;
        int n = s.length();

        while (index < n) {
            if (s.charAt(index) != '0') {
                break;
            }
            index++; // 注意! 不能在 if 里 ++, 不然会跑到 1 后面的下标上
        }

        while (index < n) {
            if (s.charAt(index) == '0') {
                suffix_0++;
            } else {
                prefix_1++;
            }
            index++;

            /*
            这里要每次都比较, 不能在最后做 return Math.min(suffix_0, prefix_1).
            因为前半段需要是0, 如果包含的 1 较少, 直接 flip, 次数会更少
            例如 input = "0101100011", exp = 3, 如果不每次做比较, 最后 prefix_1 = 5, suffix_0 = 4
             */
            suffix_0 = Math.min(suffix_0, prefix_1);
        }

        // while 循环完成时, suffix_0 要么跟 prefix_1 一样, 要么更少. 所以返回 suffix_0
        return suffix_0;
    }

}
