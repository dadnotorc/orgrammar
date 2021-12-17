/*
Easy
#Hash Table

 */
package lintcode;

import java.util.HashMap;
import java.util.List;

/**
 * 46 · Majority Element
 *
 * Given an array of integers, the majority number is the number that
 * occurs more than half of the size of the array. Find it.
 *
 * You may assume that the array is non-empty and the majority number always exist in the array.
 *
 * Example 1:
 * Input:  array = [1, 1, 1, 1, 2, 2, 2]
 * Output: 1
 *
 * Example 2:
 * Input:  array = [1, 1, 1, 2, 2, 2, 2]
 * Output: 2
 *
 * Challenge
 * O(n) time and O(1) extra space
 */
public class _0046_MajorityElement {

    /**
     * Boyer–Moore majority vote algorithm
     *
     * 参考 https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
     * 保存当前出现次数最多的元素 currentMajor 和一个 count，循环遍历 nums 所有元素，
     * 当 count 为 0 的时候把 currentMajor 设为当前遍历到的元素。
     * 时间复杂度为 O(N), 额外空间复杂度为 O(1)
     * 一个简单例子： nums: 1 1 1 2 2 2 2 currentMajor: 1 1 1 1 1 2 2 count: 0 1 2 1 0 0 1
     */
    public int majorityNumber(List<Integer> nums) {
       int curMajor = 0;
       int count = 0;

        for (int num : nums) {
            if (count == 0) {
                curMajor = num;
            }

            if (num == curMajor) {
                count++;
            } else {
                count--;
            }
        }

       return curMajor;
    }

    /**
     * 使用 hashmap 记录当前数字出现次数, 当遇到超过半数的数字时即返回
     */
    public int majorityNumber_hashmap(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int size = nums.size() / 2;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > size) { // this assume the major number always exists
                return i;
            }
        }

        return 0;
    }
}
