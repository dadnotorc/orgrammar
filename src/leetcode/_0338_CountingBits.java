/*
Medium
#DP, #Bit Manipulation
 */
package leetcode;

/**
 * 338. Counting Bits
 *
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example 1:
 * Input: 2
 * Output: [0,1,1]
 *
 * Example 2:
 * Input: 5
 * Output: [0,1,1,2,1,2]
 *
 * Follow up:
 * - It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 *   But can you do it in linear time O(n) /possibly in a single pass?
 * - Space complexity should be O(n).
 * - Can you do it like a boss? Do it without using any builtin function
 *   like __builtin_popcount in c++ or in any other language.
 */
public class _0338_CountingBits {

    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        int index = 1;
        while (index <= num) {
            int size = index;
            for (int i = 0; i < size; i++) {
                dp[index++] = dp[i] + 1;
            }
        }
        return dp;
    }
}
