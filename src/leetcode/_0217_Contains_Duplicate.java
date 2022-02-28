package leetcode;

import java.util.HashSet;

/**
 * 217. Contains Duplicate
 * Easy
 *
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class _0217_Contains_Duplicate {

    /*
    暴力解法 - 两层循环, i 从 [0, n - 1], j 从 [i, n - 1], 对比 nums[i] vs nums[j].
            - O(n ^ 2)

    排序 + 遍历 - 排序后, 对比相邻两个元素是否相等.
               - O(nlogn)

    HashSet - 检查遇到的元素是否存在于在 set 中, 如果未见过, 将其放入 set
            - O(n)
     */

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            }
            set.add(i);
        }

        return false;
    }
}
