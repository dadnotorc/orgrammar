package lintcode;

import java.util.Arrays;

/**
 * 609. Two Sum - Less than or equal to target
 *
 * Given an array of integers, find how many pairs in the array such that
 *  their sum is less than or equal to a specific target number.
 * Please return the number of pairs.
 *
 * time complexity:  O(n log n)
 * space complexity: O(1)
 * 
 * sort + two pointers: 
 * 注：此题解的个数是 O(n^2)，但是由于是不等式并且只需要返回解的个数
 * 因此 Two pointers 部分是 O(n)
 */
public class _0609 {

	/**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
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
                ans += right - left; // 比right小的所有值加上left都 < target
                left++;
            }
        }
        
        return ans;
    }
}
