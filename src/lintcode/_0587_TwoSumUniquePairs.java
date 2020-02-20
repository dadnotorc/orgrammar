/*
Medium
#Two Pointers, #Hash Table
Amazon
 */
package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 587. Two Sum - Unique pairs
 *
 * Given an array of integers, find how many unique pairs in the array
 * such that their sum is equal to a specific target number.
 * Please return the number of pairs.
 *
 * Example 1:
 * Input: nums = [1,1,2,45,46,46], target = 47
 * Output: 2
 * Explanation:
 * 1 + 46 = 47
 * 2 + 45 = 47
 *
 * Example 2:
 * Input: nums = [1,1], target = 2
 * Output: 1
 * Explanation:
 * 1 + 1 = 2
 */
public class _0587_TwoSumUniquePairs {

    /**
     * 使用HashSet, 记录数组中不重复的数字
     * 1. 遍历数组, 将不重复的数字放入HashSet
     * 2. 寻找HashSet中满足条件的pair
     *    a) HashSet中无当前数字, 查找是否存在相加 == target的值, 并将其放入HashSet
     *    b) HashSet中已有当前数字
     *       * 如果target为当前值两倍, 例如 2=1+1, 找寻是否有两个1出现.
     *         注意! 已找到两个1后, 之后的1需要被忽略
     *       * 其他情况忽略并跳过
     * 数组无需排序, 因为HashSet保证使用不重复的数字
     *
     * time:  O(n) 遍历需要O(n); Hash Table: average case O(1), worst O(n)
     * space: O(n)
     */
    public int twoSum6_HashSet(int[] nums, int target) {
        if (nums == null || nums.length <= 1)
            return 0;

        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        boolean foundEqualPair = false; // 例如2=1+1, 是否已找到两个1

        for (int i : nums) {
            if (!set.contains(i)) {
                if (set.contains(target - i)) {
                    ans++;
                }
                set.add(i); // 不能先添加, 否则 nums=[1,2] target=2, 会出错
            } else {
                if ((target == i << 1) && !foundEqualPair) {
                    ans++;
                    foundEqualPair = true;
                }
            }
        }

        return ans;
    }

    /**
     * 解法2 - 双指针
     * 1. 先排序
     * 2. 左右指针分别从首尾开始向中间移动
     *    a) 首尾指针对因数字相加 == target, 则答案++；
     *       并听过数组中相同的数字
     *    b) 相加之和 < target, 移动左指针
     *    c) 相加之和 > target, 移动右指针
     */
    public int twoSum6_TwoPointers(int[] nums, int target) {
        if (nums == null || nums.length <= 1)
            return 0;

        int ans = 0, l = 0, r = nums.length - 1;

        Arrays.sort(nums);

        while (l < r) {
            if (nums[l] + nums[r] == target) {
                ans++;
                l++;
                r--;

                // 找到一对数字后, 跳过数组中相同的数字, 例如[1,1,3,3] target=4
                while (l < r && nums[l] == nums[l-1]) {
                    l++;
                }
                while (l < r && nums[r] == nums[r+1]) {
                    r--;
                }

            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }

        return ans;
    }
}