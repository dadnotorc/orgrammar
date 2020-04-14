package leetcode;

import org.junit.Test;

import java.util.HashSet;

/**
 * Counting Elements
 *
 * Given an integer array arr, count element x such that x + 1 is also in arr.
 * If there're duplicates in arr, count them separately.
 *
 * Example 1:
 * Input: arr = [1,2,3]
 * Output: 2
 * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
 *
 * Example 2:
 * Input: arr = [1,1,3,3,5,5,7,7]
 * Output: 0
 * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
 *
 * Example 3:
 * Input: arr = [1,3,2,3,5,0]
 * Output: 3
 * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
 *
 * Example 4:
 * Input: arr = [1,1,2,2]
 * Output: 2
 * Explanation: Two 1s are counted cause 2 is in arr.
 *
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 */
public class _x_CountingElements {

    /**
     * 易错点:
     * 1. 第一次loop - 遍历原数组
     *    第二次loop - 遍历新数组
     * 2. 认真读题
     *    [1,1,2] ->　exp output = 2
     */
    public int countElements(int[] arr) {
        int ans = 0;
        int[] counts = new int[1001]; // 取1001因为原数组中数字范围是[0,1000], 共1001个数字

        for (int i : arr) {
            counts[i]++;
        }

        for (int i = 0; i < 1000; i++) { // i的范围是[0,999]
            if (counts[i] != 0 && counts[i+1] != 0) {
                ans += counts[i]; // 不管i有几个, 只要i+1有一个就够
            }
        }

        return ans;
    }


    /**
     * 因为不用考虑每个数字的出现次数 (不管i有几个, 只要i+1有一个就够), 所以用HashSet即可
     */
    public int countElements_2(int[] arr) {
        int ans = 0;
        HashSet<Integer> set = new HashSet<>(); // 用HashSet而不用HashMap, 因为无需考虑i+1有几个, 只要不为0就行

        for (int i : arr) {
            set.add(i);
        }

        for (int i : arr) {
            if (set.contains(i+1)) {
                ans++;
            }
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] arr = {1,1,2};
        org.junit.Assert.assertEquals(2, countElements(arr));
    }


}
