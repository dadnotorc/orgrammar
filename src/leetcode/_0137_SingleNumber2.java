/*
Medium
#Bit Manipulation
 */
package leetcode;

/**
 * 137. Single Number II
 *
 * Given a non-empty array of integers, every element appears three times except for one,
 * which appears exactly once. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 * Input: [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class _0137_SingleNumber2 {

    /**
     * 统计所有数字在32位中第i位为1的个数, 并对此个数取模3.
     * 对于3个重复出现的数字, 第i位求和取模之后为0. 对于单个出现的数字, 这里保留它的第i位. 例如
     * 0 0 1
     * 0 0 1
     * 1 0 1
     * 0 0 1
     * ----- 求和后取模3
     * 1 0 1
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) { // 检查int 32位中的每一位
            // 取位
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1; // 统计所有数字在第i位是1的个数,
            }
            sum %= 3; // 对1的个数取模3

            // 返还
            res |= sum << i;
        }
        return res;
    }
}
