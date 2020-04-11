/*
Easy
#Array
Google
FAQ++
 */
package lintcode;

/**
 * 1212. Max Consecutive Ones
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Notice
 * - The input array will only contain 0 and 1.
 * - The length of input array is a positive integer and will not exceed 10,000
 *
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *     The maximum number of consecutive 1s is 3.
 *
 * Example 2:
 * Input: [1]
 * Output: 1
 */
public class _1212_MaxConsecutiveOnes {

    /**
     * 易错点:
     * 1. return前, 别忘了做最后一次比较
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int curLen = 0;

        for (int i : nums) {
            if (i == 0) {
                ans = Math.max(ans, curLen);
                curLen = 0;
            } else {
                curLen++;
            }
        }

        return Math.max(ans, curLen); // 别忘了最后这一次的比较
    }
}
