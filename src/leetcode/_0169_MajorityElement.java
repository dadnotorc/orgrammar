/*
Easy
#Array, #Divide and Conquer, #Bit Manipulation
 */
package leetcode;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

/**
 * 169. Majority Element
 *
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class _0169_MajorityElement {

    /**
     * 使用HashMap
     * 这种写法假设the majority element一定存在
     *
     * 也可以将与threshold的比较放在if else block之后, 但是速度稍慢一点
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int threshold = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            if (map.containsKey(i)) {
                int count = map.get(i) + 1;
                if (count > threshold) {
                    return i;
                }
                map.put(i, count);
            } else {
                map.put(i, 1);
            }
        }

        return 0;
    }

    /**
     * 使用sorting
     */
    public int majorityElement_1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
