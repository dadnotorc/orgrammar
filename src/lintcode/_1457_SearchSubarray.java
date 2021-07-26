/*
Medium
#Hash Table, #prefix sum, #array
Amazon
 */
package lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1457. Search Subarray
 *
 * Given an array arr and a non-negative integer k, you need to find a continuous array from this array
 * so that the sum of this array is k. Output the length of this array.
 * If there are multiple such substrings, return the one with the minimum ending position;
 * if there are multiple answers, return the one with the minimum starting position;
 * If no such subarray is found, -1 is returned.
 *
 * - the length of the array does not exceed 10^6
 * - each number in the array is less than or equal to 10^6,
 * - k does not exceed 10^6
 *
 * Example 1 :
 * Input：arr=[1,2,3,4,5] ，k=5
 * Output：2
 * Explanation:
 * In this array, the earliest contiguous substring is [2,3].
 *
 * Example 2 :
 * Input：arr=[3,5,7,10,2] ，k=12
 * Output：2
 * Explanation:
 * In this array, the earliest consecutive concatenated substrings with a sum of 12 are [5,7].
 */
public class _1457_SearchSubarray {

    /*
    使用HashMap记录<前缀和,下标>.
     */
    public int searchSubarray(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // key 为 prefix sum, value 为下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (map.containsKey(prefixSum - k)) { // 注意顺序, 是 前缀和 - k
                return i - map.get(prefixSum - k);
            }
            if (!map.containsKey(prefixSum)) {
                // 如果不做此判断, 就无法找到最小的开始下标
                // 例如 arr=[3,-3,1] ，k=1. 当 i 指向 -3 时, prefixSum 再次等于 0.
                map.put(prefixSum, i);
            }
        }

        return -1;
    }

}
