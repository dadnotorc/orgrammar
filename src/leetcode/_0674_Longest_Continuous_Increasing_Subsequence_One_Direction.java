package leetcode;

import org.junit.Test;

/**
 * 674. Longest Continuous Increasing Subsequence - only from left to right
 * Easy
 * #Array
 *
 * Given an unsorted array of integers nums, return the length of the
 * longest continuous increasing subsequence (i.e. subarray).
 * The subsequence must be strictly increasing.
 *
 * A continuous increasing subsequence is defined by two indices l and r (l < r)
 * such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and
 * for each l <= i < r, nums[i] < nums[i + 1].
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence,
 * it's not a continuous one where 5 and 7 are separated by 4.
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 *
 * Note: Length of the array will not exceed 10,000.
 *
 * 与 lintcode 397 不同之处
 * - 397 中, 如果是 降序 也可以 (也就是 升序 从左到右 或者 从右到左)
 */
public class _0674_Longest_Continuous_Increasing_Subsequence_One_Direction {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 1, curMax = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curMax++;
            }
            else {
                ans = Math.max(ans, curMax);
                curMax = 1;
            }
        }

        return Math.max(ans, curMax); // 注意, 如果直接return ans就会出错. 因为ans在循环中可能从未更新过, 比如 {1,3,5,7}
    }


    /**
     * 简约解法 - 把之前的 if else 语句用一句写出来
     */
    public int findLengthOfLCIS_v2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 1, curMax = 1;

        for (int i = 1; i < nums.length; i++) {
            curMax = nums[i] > nums[i - 1] ? curMax + 1 : 1;
            ans = Math.max(ans, curMax);
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1,3,5,4,7};
        org.junit.Assert.assertEquals(3, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(3, findLengthOfLCIS_v2(nums));
    }

    @Test
    public void test2() {
        int[] nums = {2,2,2,2,2};
        //org.junit.Assert.assertEquals(1, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(1, findLengthOfLCIS_v2(nums));
    }

    @Test
    public void test3() {
        int[] nums = {1,3,5,7};
        //org.junit.Assert.assertEquals(4, findLengthOfLCIS(nums));
        org.junit.Assert.assertEquals(4, findLengthOfLCIS_v2(nums));
    }
}
