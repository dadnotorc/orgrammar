/*
Easy
#Two Pointers, #Array, #Hash Table, #Sort
Adobe, Airbnb, Amazon, Apple, Dropbox, Facebook, LinkedIn, Microsoft, Uber
FAQ++
 */
package lintcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 56. Two Sum
 *
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 *
 * The function twoSum should return indices of the two numbers such that
 * they add up to the target, where index1 must be less than index2. Please
 * note that your returned answers (both index1 and index2) are zero-based.
 *
 * Notice
 * - You may assume that each input would have exactly one solution
 *
 * Example1:
 * numbers=[2, 7, 11, 15], target=9
 * return [0, 1]
 *
 * Example2:
 * numbers=[15, 2, 7, 11], target=9
 * return [1, 2]
 *
 * Challenge - Either of the following solutions are acceptable:
 * - O(n) Space, O(nlogn) Time (排序 + 双指针)
 * - O(n) Space, O(n) Time     (一次遍历)
 *
 * 如果使用排序+双指针, 需要创建class, 记录某个数字的原始index, 以及value
 */
public class _0056_TwoSum {

    /**
     * HashMap key = target - curVal, value = index
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[] {-1, -1};

        if (numbers == null || numbers.length < 2)
            return ans;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                ans[0] = map.get(numbers[i]);
                ans[1] = i;
                return ans;
            }
            map.put(target - numbers[i], i);
        }

        return ans;
    }

    /**
     * 排序 + 双指针
     * 需要创建class, 记录某个数字的原始index, 以及value
     *
     * 易错点:
     * 1. 返回值的前者应该小于后者, 因为sorting改变了index, 所以左指针位置的original index未必小于右指针
     *    所以返回值前者必须取小, 后者取大
     */
    class NumWithIndex {
        int index, val;
        public NumWithIndex(int index, int val) {
            this.index = index; // 记录sorting之前的原始index
            this.val = val;
        }
    }

    public int[] twoSum_2(int[] numbers, int target) {
        int[] ans = new int[] {-1, -1};

        if (numbers == null || numbers.length < 2)
            return ans;

        // 第一次遍历, 记录原始index
        NumWithIndex[] nums = new NumWithIndex[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = new NumWithIndex(i, numbers[i]);
        }

        // sort
        Arrays.sort(nums, new Comparator<NumWithIndex>() {
            @Override
            public int compare(NumWithIndex num1, NumWithIndex num2) {
                return num1.val - num2.val;
            }
        });

        // 双指针分别指向首尾
        int l = 0, r = nums.length - 1;

        while (l < r) {
            if (nums[l].val + nums[r].val == target) {
                ans[0] = Math.min(nums[l].index, nums[r].index); // 别忘了前者的index应该较小
                ans[1] = Math.max(nums[l].index, nums[r].index);
                return ans;
            }

            if (nums[l].val + nums[r].val < target) {
                l++;
            } else {
                r--;
            }
        }

        return ans;
    }
}
