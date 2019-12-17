/*
Easy

LinkedIn
 */
package lintcode;

import org.junit.Test;

/**
 * 1814. Sum of all Subarrays
 *
 * Given a list of nums, return the sum of all subarrays.
 * Notice
 *  - if nums = [2, 4, 1], subarrays is {[2], [4], [1], [2, 4], [4, 1], [2, 4, 1]}
 *  - Guaranteed that the result returned is the type of int
 *
 * Example1:
 * Input: nums = [1, 2, 3]
 * Output: 20
 * Explanation : {1} + {2} + {3} + {2 + 3} + {1 + 2} + {1 + 2 + 3} = 20
 *
 * Example2
 * Input : [1, 2]
 * Output : 6
 * Explanation :{1} + {2} + {1, 2} = 6
 */
public class _1814_SumofAllSubarrays {

    public int subArraySum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            /*
            每个数字可与其左边和右边组合
            该数字出现的次数 = (i + 1) * (n - i)
             */
            ans += nums[i] * (i + 1) * (n - i);
        }
        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 3};
        org.junit.Assert.assertEquals(20, subArraySum(nums));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2};
        org.junit.Assert.assertEquals(6, subArraySum(nums));
    }

    @Test
    public void test3() {
        int[] nums = {1, 2, 3, 4};
        org.junit.Assert.assertEquals(50, subArraySum(nums));
    }
}
