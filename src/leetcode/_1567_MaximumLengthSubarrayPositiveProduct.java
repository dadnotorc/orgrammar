/*
Medium
#Array, #DP, #Greedy
Amazon
 */
package leetcode;

import org.junit.Test;

/**
 * 1567. Maximum Length of Subarray With Positive Product
 *
 * Given an array of integers nums, find the maximum length of a subarray
 * where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 * Example 1:
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 *
 * Example 2:
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 *
 * Example 3:
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class _1567_MaximumLengthSubarrayPositiveProduct {


    /**
     * space 优化, 用两个 int 值 取代两个 int 数组
     *
     * pos, neg - the longest subarray ending with the current number forming a positive/negative product
     */
    public int getMaxLen_dp_2(int[] nums) {
        // 乘积为正 / 负的子数组长度
        int pos = 0;
        int neg = 0;

        int ans = 0;

        for (int i : nums) {
            if (i > 0) {
                pos++;
                neg = neg > 0 ? 1 + neg : 0; // 只有当 neg > 0 的时候, 才能 +1. 之前未遇到负数的话, 就不变
            } else if (i < 0) {
                // 这里要小心, 必须引入一个临时参数, 不然 pos neg 的值会混乱
                // 而且 正负要 swap
                int temp_pos = pos;
                pos = neg > 0 ? 1 + neg : 0; // 负负得正
                neg = temp_pos + 1; // 正负得负
            } else {
                // 不希望子数组中带上 0, 所以两者都 reset
                pos = 0;
                neg = 0;
            }

            // 每次循环, 都需要比较 pos 与当前值
            ans = Math.max(pos, ans);
        }

        return ans;
    }



    /**
     * DP
     * pos[i] - 到当前 i 下标, 乘积为正 且最长的 subarray 的长度
     * neg[i] - 到当前 i 下标, 乘积为负 且最长的 subarray 的长度
     *
     * "pos[i]", "neg[i]" represent longest consecutive numbers ending with nums[i] forming a positive/negative product.
     *
     * 时间 O(n)
     * 空间 O(n)
     */
    public int getMaxLen_dp(int[] nums) {
        int n = nums.length;
        int[] pos = new int[n];
        int[] neg = new int[n];

        if (nums[0] > 0) { pos[0] = 1; }
        if (nums[0] < 0) { neg[0] = 1; }

        int ans = pos[0]; // ans 从 pos[0] 开始, 因为答案要求是乘积为正的 subarray

        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                pos[i] = 1 + pos[i - 1]; // 乘积为正的子数组长度 +1
                neg[i] = neg[i - 1] > 0 ? 1 + neg[i - 1] : 0; // 乘积为负的子数组长度也 +1 (如果之前不为 0 的话)

            } else if (nums[i] < 0) {
                pos[i] = neg[i - 1] > 0 ? 1 + neg[i - 1] : 0; // 之前乘积为负, 现在变正了. 长度 + 1
                neg[i] = 1 + pos[i - 1];  // 乘积为负的子数组长度 +1
            }

            // 这里不写 if (nums[i] == 0) 是因为, pos[i] 和 neg[i] 都默认是 0

            ans = Math.max(ans, pos[i]); // 记得每次都要更新 ans
        }
        return ans;
    }


    /**
     * 上岸解法 - 遍历题，由于涉及到负数相乘，因此需要区分负数出现的个数。并且注意到0的存在会影响结果，
     * 因此在遍历的过程中巧妙的将两个零之间的一小段区间当做我们处理的目标。然后得出全局最大值即可，详情见注释。
     */
    public int getMaxLen_shangan(int[] nums) {
        int negativeIndex = -1, zeroIndex = -1;
        int totalNegatives = 0, res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // 记录当前这一段区间（可以理解成两个0之间的那一段区间），负数第一次出现的位置
            if (nums[i] < 0) {
                totalNegatives++;
                if (negativeIndex == -1) {
                    negativeIndex = i;
                }
            }
            // 注意，每当遇到一次0，负数的总个数清零重新计算，因为子数组中我们不希望含有0
            if (nums[i] == 0) {
                zeroIndex = i;
                totalNegatives = 0;
                negativeIndex = -1;
            }
            // 根据负数的个数，来截取子数组的长度
            else {
                if (totalNegatives % 2 == 0) {
                    res = Math.max(i - zeroIndex, res);
                } else {
                    res = Math.max(i - negativeIndex, res);
                }
            }
        }
        return res;
    }



    /**
     * 有bug, 无法处理 [6,2,10,1,-2,8]
     */
    public int getMaxLen_bug(int[] nums) {
        int ans = 0;
        int dp = 0;

        int lastIndex = -1; // last index of the unmatched -1
        int lastMax = 0; // the max len before meeting the unmatched -1

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                lastIndex = -1; // 遇到 0 时, 不尽要清零 dp, 还要重置 lastIndex
                dp = 0;
            } else if (nums[i] < 0) {
                if (lastIndex == -1) { // 表示未遇见过 -1
                    lastIndex = i; // 既然要涉及到下标, 就别用 for (int i : nums)
                    lastMax = dp;
                    dp = 0;
                } else {
                    lastIndex = -1; // 找到 matched -1后, 将其重置
                    dp += lastMax + 2; // +2 是加上两个 -1
                }
            } else { // nums[i] > 0
                dp += 1;
            }
            ans = Math.max(ans, dp);
        }

        if (lastIndex != -1) {
            ans = Math.max(ans, dp + lastMax);
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1,2,3,4,-1,1,2,3,4,5,-1,1,2,3,4,5,6};
        org.junit.Assert.assertEquals(17, getMaxLen_shangan(nums));

        nums = new int[] {-16,0,-5,2,2,-13,11,8};
        org.junit.Assert.assertEquals(6, getMaxLen_shangan(nums));

        nums = new int[] {-7,-10,-7,21,20,-12,-34,26,2};
        org.junit.Assert.assertEquals(8, getMaxLen_shangan(nums));

        nums = new int[] {6,2,10,1,-2,8};
        org.junit.Assert.assertEquals(4, getMaxLen_shangan(nums));
    }
}
