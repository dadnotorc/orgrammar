/*
Medium
Sort, Quick Sort, Array
Google, Salesforce
 */
package lintcode.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 508. Wiggle Sort
 *
 * Given an unsorted array nums, reorder it in-place such that
 *
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Notice
 * - Please complete the problem in-place.
 *
 * Example 1:
 * Input: [3, 5, 2, 1, 6, 4]
 * Output: [1, 6, 2, 5, 3, 4]
 * Explanation: This question may have multiple answers, and [2, 6, 1, 5, 3, 4] is also ok.
 *
 * Example 2:
 * Input: [1, 2, 3, 4]
 * Output: [1, 4, 2, 3]
 */
public class _0508_WiggleSort {

    // 从左到右扫一遍，不满足条件的交换就好了。
    // 需要证明的是，当我们 交换了 nums[i] 和 nums[i - 1] 以后：
    // nums[i - 2], nums[i], nums[i - 1]
    // nums[i - 2] 不会和 nums[i] 形成逆序（不满足条件的大小关系）
    //
    // 那假如原来是 nums[i - 2] <= nums[i - 1]，那么 nums[i - 1] 和 nums[i] 交换的条件是，nums[i - 1] < nums[i]。
    // 那我们就推导出此时 nums[i] > nums[i - 2]，因此交换之后，不会让 nums[i] 和 nums[i - 2] 的大小关系出现变化。
    //
    // 反过来如果 nums[i - 2] >= nums[i - 1] 的情况同理。
    // time:  O(n)
    // space: O(1)
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        for (int i = 1; i < nums.length; i++) {
            if (((i & 1) == 1 && nums[i - 1] > nums[i]) ||     // i为奇数
                    ((i & 1) == 0 && nums[i - 1] < nums[i])) { // i为偶数
                swap(nums, i - 1, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 解法 2 */
    // 先排序, 再调整位置
    // time:  O(nlogn)
    // space: O(1)
    public void wiggleSort_2(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        Arrays.sort(nums);
        int length = nums.length;
        int left = 1, right = length - 1;
        // 长度为奇数, 第二位和末尾互换, 第四位和倒数第三位互换...
        // 长度为偶数, 第二位和倒数第二位互换, 第四位和倒数第四位互换...
        if ((length & 1) == 0) {
            right -= 1;
        }
        while (left < right) {
            swap(nums, left, right);
            left += 2;
            right -= 2;
        }
    }

    @Test
    public void test1() {
        int[] input = {3, 5, 2, 1, 6, 4};
        int[] exp = {3, 5, 1, 6, 2, 4};
        wiggleSort(input);
        org.junit.Assert.assertArrayEquals(exp, input);
    }

    @Test
    public void test2() {
        int[] input = {1, 2, 3, 4};
        int[] exp = {1, 3, 2, 4};
        wiggleSort(input);
        org.junit.Assert.assertArrayEquals(exp, input);
    }

    @Test
    public void test3() {
        int[] input = {1};
        int[] exp = {1};
        wiggleSort(input);
        org.junit.Assert.assertArrayEquals(exp, input);
    }

    @Test
    public void test4() {
        int[] input = {};
        int[] exp = {};
        wiggleSort(input);
        org.junit.Assert.assertArrayEquals(exp, input);
    }
}
