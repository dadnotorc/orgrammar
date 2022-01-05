package interviews;

/**
 * Amazon OA - 2022 年 1月
 *
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
 * followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips ot make s monotone increasing
 *
 * Example 1:
 * input: "00110"
 * output: 1
 * Explanation: flip the last digit to get 00111
 *
 * Example 2:
 * input: "010110"
 * output: 2
 * Explanation: fip to 011111 OR 000111
 *
 * Example 3:
 * input = "00011000"
 * output: 2
 * Explanation: fip to 00000000
 *
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'
 *
 * leetcode 926. Flip String to Monotone Increasing
 */
public class AMZN_2022_Flip_String_to_Monotone_Increasing {

    /**
     * 上岸解法 - 根据题目要求，转化之后的字符串只能是如下三种：
     * - 全是 0
     * - 全是 1
     * - 前半段是0，后半段是 1
     * 因此在计算过程中只需要考虑，到当前位置为止，以上三种转化方式最小的一种即可。
     */
    public int getLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int prefix_1 = 0; // 需要 从左向右 把 1 转成 0 的个数
        int suffix_0 = 0; // 需要 从有向左 把 0 转成 1 的个数

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '0') {
                suffix_0 = Math.min(prefix_1, suffix_0 + 1);
            } else {
                prefix_1++;
            }
        }

        return suffix_0;

        // 不用再做比较, 因为 suffix_0 肯定 <= prefix_1
        // return Math.min(prefix_1, suffix_0);
    }

    /**
     * prefix-suffix 解法 - 修改自 leetcode 926. Flip String to Monotone Increasing
     * 1. 跳过开头的 0, 直到遇到第一个 1
     * 2. 记录 1 的出现次数 - prefix_1
     * 3. 遇到 1 以后, 之后遇到的所有 0 也是 potential candidate, 要记录出现次数 - suffix_0
     * 4. 遍历完, 比较 prefix_1 与 suffix_0, 并返回较少者
     */
    public int getLength_2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 可 flip 的 1 与 0 的个数
        int prefix_1 = 0, suffix_0 = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '0') {
                if (prefix_1 != 0) {
                    suffix_0 = Math.min(prefix_1, suffix_0 + 1); // 后者 +1 是因为这个 0 本身是 flip candidate
                }

                // 如果 prefix_1 == 0, 说明遇到的是开头的 0, 需要跳过, 直到遇到第一个 1. 这里可以省略掉
            } else {
                prefix_1++;
            }
        }

        // while 循环完成时, suffix_0 要么跟 prefix_1 一样, 要么更少. 所以返回 suffix_0
        return suffix_0;
    }


    /**
     * 题目的意思是, split string into left '0' and right '1', 而且 '1'->'0' flip (left) 与 '0'->'1' flip (right) 最少
     * 1. Count of '1' -> '0' flips going left to right, and store it in f0.
     *    Count of '0' -> '1' flips going right to left, and store it in f1.
     * 2. Find the smallest f0[i] + f1[i].
     */
    public int getLength_dp(String s) {
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
}
