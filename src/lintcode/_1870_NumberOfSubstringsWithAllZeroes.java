/*
Medium
#Two Pointers, #String
Google
 */
package lintcode;

/**
 * 1870 · Number of Substrings with All Zeroes
 *
 * Given a string str containing only 0 or 1, please return the number of substrings that consist of 0 .
 *
 * 1 <= |str| <= 30000
 *
 * Example 1:
 * Input: "00010011"
 * Output: 9
 * Explanation:
 * There are 5 substrings of "0",
 * There are 3 substrings of "00",
 * There is 1 substring of "000".
 * So return 9
 *
 * Example 2:
 * Input: "010010"
 * Output: 5
 */
public class _1870_NumberOfSubstringsWithAllZeroes {

    /**
     * n 个 0 的子串有 n + (n -1) + ... + 2 + 1 个
     * 例如 "000" -> 3 + 2 + 1 = 6 个
     * "0000" -> 4 + 3 + 2 + 1 = 10 个
     */

    /**
     * 易错点, 遍历完后, 别忘了最后还要再加一次 - 例如 "000"
     */

    /**
     * 九章解法 - 类似前一种, 统计连续 0 的个数, 然后计算
     */
    public int stringCount_9z(String str) {
        if (str == null || str.length() == 0) { return 0; }

        int ans = 0, count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                count++;
            } else {
                ans += (1 + count) * count / 2;
                count = 0;
            }
        }

        // 别忘了最后这次
        ans += (1 + count) * count / 2;

        return ans;
    }

    /**
     * 双指针
     * - l 指针指向第一个 0
     * - r 指针遍历
     *    - 当 r 指向 0 时, 跳过
     *    - 当 r 指向 1 时, 说明有 r - l 个0
     *       - 计算 r - l 个 0 有多少子串
     *       - 更新 l 到 r + 1 (注意是 + 1, 到下一位)
     *
     * 注意全是 0 的情况, "000"
     */
    public int stringCount(String str) {
        if (str == null || str.length() == 0) { return 0; }

        int ans = 0;
        int l = 0, r = 0;
        char[] nums = str.toCharArray();
        for (; r < nums.length; r++) {
            if (nums[r] == '1') {
                ans += getSum(r - l);
                l = r + 1;
            }

            // when == '0', simply increment r
        }

        ans += getSum(r - l); // 别忘了最后再加一次, 例如 "000"

        return ans;
    }

    // 计算 1 + 2 + ... + i
    public int getSum(int i) {
        return (1 + i) * i / 2;
    }
}
