package lintcode.twosum;

import java.util.Arrays;

/**
 * 609. Two Sum - Less than or equal to target
 *
 * Given an array of integers, find how many pairs in the array such that
 *  their sum is less than or equal to a specific target number.
 * Please return the number of pairs.
 */
public class _0609_TwoSumLessOrEqual {

	/**
     * time:  O(nlogn)
     * space: O(1)
     *
     * sort + 2 pointers
     * 此题解的个数是O(n^2), 但由于是不等式且只需要返回解的个数. 因此 2 pointers部分是O(n)
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        int ans = 0;
        
        if (nums == null || nums.length < 2) {
            return ans;
        }
        
        // Don't forget to sort first
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                ans += right - left; // ��rightС������ֵ����left�� < target
                left++;
            }
        }
        
        return ans;
    }
}
