/*
Medium
#Binary Search
Amazon, LinkedIn
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1536. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n). <-> 二分法
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class _1536_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 两次Binary Search, 先定左段, 再顶右段
     */
    public List<Integer> searchRange(List<Integer> nums, int target) {
        List<Integer> ans = new ArrayList<>();
        int start = -1, end = -1;

        if (nums == null || nums.size() == 0 || !nums.contains(target)) {
            ans.add(start);
            ans.add(end);
            return ans;
        }

        int l = 0, r = nums.size() -1, mid = 0;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (nums.get(mid) == target) {
                start = mid;
                r = mid;
            } else if (nums.get(mid) < target) {
                l = mid;
            } else {
                r = mid;
            }
        }

        // 无需做下列判断, 因为第38行已保证target一定存在
//        if (start == -1) {
//            // 第一次while循环做完, 如果start仍为-1, 说明只有 l 或者 r 可能等于targe
//            if (nums.get(l) == target && nums.get(r) == target) {
//                start = l;
//                end = r;
//            } else if (nums.get(l) == target) {
//                start = end = l;
//            } else if (nums.get(r) == target) {
//                start = end = r;
//            }
//            ans.add(start);
//            ans.add(end);
//            return ans;
//        }

        // 已找到target, start = r, 则查看 l 是否也等于target
        start = nums.get(l) == target ? l : r;

        l = start;
        r = nums.size() - 1;

        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (nums.get(mid) == target) {
                end = mid;
                l = mid;
            } else { // mid值只可能大于等于target
                r = mid;
            }
        }

        end = nums.get(r) == target ? r : l;

        ans.add(start);
        ans.add(end);
        return ans;
    }
}
