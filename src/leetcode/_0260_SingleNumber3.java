/*
Medium
#Bit Manipulation
 */
package leetcode;

/**
 * 260. Single Number III
 *
 * Given an array of numbers nums, in which exactly two elements appear only once and
 * all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 * Example:
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 *
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant space complexity?
 */
public class _0260_SingleNumber3 {

    /**
     * 两次遍历数组, 找出只出现过一次的两个数, a 与 b
     * 1. 第一次对所有数字使用^, 所得的值为 a^b
     * 2. 此时将原数组以分为两组, 并将a与b划分到不同的组内. 已知 a != b, 所以两者肯定至少有一位值不同, 即 a^b至少有一位为1
     *    使用计算last set bit方法, (A & -A), 得出最低位
     *    以此划分数组
     * 3. 再次遍历数组,
     *    如果当前数字 the bit is not set, 将其与第一组其他数字做 ^ 异或运算
     *    如果当前数字 the bit is set, 将其与第二组其他数字做 ^ 异或运算
     *    因为 a与b 分属不同组, 对每个组做 ^ 异或运算, 将分别得出 a与b
     */
    public int[] singleNumber(int[] nums) {
        // 第一轮遍历, 使用^. 假设a与b是答案, 此轮遍历得出a^b
        // a != b, 所以肯定有至少 1bit 值不同
        int xorVal = 0;
        for (int num : nums) {
            xorVal ^= num;
        }

        // get the last set bit
        // 以此将原数组分成两组,
        xorVal &= -xorVal;

        // 用xorVal将原数组分成两组, a与b被划分到不同的组内, 此时再次使用^, 获得a与b
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & xorVal) == 0) { // 第一组, the bit is not set
                res[0] ^= num;
            } else { // 第二组, the bit is set
                res[1] ^= num;
            }
        }

        return res;
    }
}
