/*
Medium
Bit Manipulation
Facebook
 */
package lintcode;

import org.junit.Test;

/**
 * 1217. Total Hamming Distance
 *
 * The Hamming distance between two integers is the number of positions
 * at which the corresponding bits are different.
 *
 * Now your job is to find the total Hamming distance between all pairs
 * of the given numbers.
 *
 * Notice
 * - Elements of the given array are in the range of 0 to 10^9
 * - Length of the array will not exceed 10^4.
 *
 * Example 1:
 * Input: [4, 14, 2]
 * Output: 6
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110,
 * and 2 is 0010 (just showing the four bits relevant in this case).
 * So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2)
 * = 2 + 2 + 2 = 6.
 *
 * Example 2:
 * Input: [2, 1, 0]
 * Output: 4
 * Explanation: In binary representation, the 2 is 10, 1 is 01, and
 * 0 is 00 (just showing the four bits relevant in this case).
 * So the answer will be:
 * HammingDistance(2, 1) + HammingDistance(1, 0) + HammingDistance(2, 0)
 * = 2 + 1 + 1 = 4.
 */
public class _1217_TotalHammingDistance {

    /**
     * 解法1 - 假设32位机器
     */
    public int totalHammingDistance_1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 0, n = nums.length;

        for (int j = 0; j < 32; j++) { // 32位
            int bitCount = 0;

            for (int i = 0; i < n; i++) { // 检查数组中的每个数字
                bitCount += (nums[i] >> j) & 1;
            }

            // bitCount     = 当前位 1 的个数之和
            // n - bitCount = 当前位 0 的个数之和
            // 两者的乘积为当前位的汉明距离
            // 最后将所有位的汉明距离加起来
            ans += bitCount * (n - bitCount);
        }

        return ans;
    }


    /**
     * 解法2 - 使用bitwise XOR 计算两个int之间的bits difference
     */
    public int totalHammingDistance_2(int[] nums) {
        // 使用 >> 做bits operation
        // 计算hamming distance的公式 = 当前位1的数量 X 当前位0的数量

        if (nums == null || nums.length <= 1)
            return 0;

        int ans = 0;

        for (int i = 0; i < nums.length -1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ans += hammingDistance(nums[i], nums[j]);
            }
        }

        return ans;
    }

    private int hammingDistance(int x, int y) {
        int tmp = x ^ y; // bitwise XOR -> 某个bit为1 表示当前位不同

        //统计出tmp中有多少位1, 即有多少位不同
        int count = 0;

        while (tmp != 0) {
            count++;
            tmp = tmp & (tmp - 1);
        }

        return count;
    }

    @Test
    public void test0() {
        int[] nums = {14, 14};
        org.junit.Assert.assertEquals(0, totalHammingDistance_1(nums));
        org.junit.Assert.assertEquals(0, totalHammingDistance_2(nums));
    }

    @Test
    public void test1() {
        int[] nums = {4, 14, 2};
        org.junit.Assert.assertEquals(6, totalHammingDistance_1(nums));
        org.junit.Assert.assertEquals(6, totalHammingDistance_2(nums));
    }

    @Test
    public void test2() {
        int[] nums = {2, 1, 0};
        org.junit.Assert.assertEquals(4, totalHammingDistance_1(nums));
        org.junit.Assert.assertEquals(4, totalHammingDistance_2(nums));
    }
}