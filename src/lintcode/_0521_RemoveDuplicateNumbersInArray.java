/*
Easy
#Hash Table, #Two Pointers, #Array
 */
package lintcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 521 · Remove Duplicate Numbers in Array
 *
 * Given an array of integers, remove the duplicate numbers in it. You should:
 * - Do it in place in the array.
 * - Put the element after removing the repetition at the beginning of the array. 不重复的字符放在前面
 * - Return the number of elements after removing duplicate elements.
 *
 * You don't need to keep the original order of the integers.
 *
 * Example 1:
 * Input: nums = [1,3,1,4,4,2]
 * Output:[1,3,4,2,?,?] - 4
 *
 * Explanation:
 * Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
 * Return the number of unique integers in nums => 4.
 * Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [1,2,3] - 3
 *
 * Challenge
 * 1. Do it in O(n) time complexity.
 * 2. Do it in O(nlogn) time without extra space. - 排序?
 */
public class _0521_RemoveDuplicateNumbersInArray {

    /**
     * 使用 HashSet 记录字符, 保证不重复
     * - l 指针指向不重复字符的下一位
     * - r 指针遍历字符串寻找不重复的字符
     */
    public int deduplication(int[] nums) {
        if(nums == null || nums.length == 0) { return 0; }

        HashSet<Integer> set = new HashSet<>();
        int l = 0, r = 0;
        while (r < nums.length) {
            if (!set.contains(nums[r])) {
                if (l != r) {
                    nums[l] = nums[r];
                }
                set.add(nums[r]);
                l++;
            }
            r++;
        }

        return l; // 注意 这里不能再 +1 了, 因为 while 循环中已经 ++ 过, 到达下一位
    }

    /**
     * 1. 排序
     * 2. 遍历
     *    - l 指针指向不重复字符的最后一位 (而不是下一位), 所以每次更新时, 要先将 l 指针后移
     *    - r 指针遍历
     *
     * [1,2,2,3]
     */
    public int deduplication_sort(int[] nums) {
        if(nums == null || nums.length == 0) { return 0; }

        Arrays.sort(nums);

        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[l] != nums[r]) {
                l++; // 要先 ++, 后比较
                if (l != r) {
                    nums[l] = nums[r];
                }
            }
        }
        return l + 1; // 因为 l 指向的是不重复字符的最后一位, 所以这里要 + 1
    }
}
