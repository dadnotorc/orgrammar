package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;

import static org.junit.Assert.assertArrayEquals;

/**
 * 1122. Relative Sort Array
 * Easy
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct,
 *  and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items
 *  in arr1 are the same as in arr2.  Elements that don't appear in
 *  arr2 should be placed at the end of arr1 in ascending order.
 *
 * Example 1:
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * Constraints:
 * - arr1.length, arr2.length <= 1000
 * - 0 <= arr1[i], arr2[i] <= 1000
 * - Each arr2[i] is distinct.
 * - Each arr2[i] is in arr1.
 */
public class _1122_RelativeSortArray {

    /**
     * LC上的参考解法
     */
    public int[] relativeSortArray_1(int[] arr1, int[] arr2) {
        int k = 0;
        int[] cnt = new int[1001], ans = new int[arr1.length];
        for (int i : arr1)
            ++cnt[i];
        for (int i : arr2)
            while (cnt[i]-- > 0)
                ans[k++] = i;
        for (int i = 0; i < 1001; ++i)
            while (cnt[i]-- > 0)
                ans[k++] = i;
        return ans;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        Hashtable<Integer, Integer> tb = new Hashtable<>();
        for (int i = 0; i < arr2.length; i++) {
            tb.put(i, arr2[i]);
        }

        int[] ans = new int[arr1.length];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int j = 0; j < arr1.length; j++) {
            if (tb.containsValue(arr1[j])) {
                if (map.containsKey(arr1[j])) {
                    map.put(arr1[j], map.get(arr1[j]) + 1);
                } else {
                    map.put(arr1[j], 1);
                }
            } else {
                pq.add(arr1[j]);
            }
        }

        int index = 0;

        for (int i = 0; i < tb.size(); i++) {
            Integer curInt = tb.get(i);
            Integer curIntSz = map.get(curInt);
            for (int j = 0; j < curIntSz; j++) {
                ans[index++] = curInt;
            }
        }

        /**
         * 不能使用 iterator 或者 for (Interger i : pq)
         * 因为pq implements as a priority heap rather than sorted list
         */
        while (!pq.isEmpty()) {
            ans[index++] = pq.poll();
        }

        return ans;
    }


    /**
     * Input:
     * [33,22,48,4,39,36,41,47,15,45]
     * [22,33,48,4]
     * Output:
     * [22,33,48,4,15,36,41,47,39,45]
     * Expected:
     * [22,33,48,4,15,36,39,41,45,47]
     */

    @Test
    public void test1() {
        //int[] arr1 = {33,22,48,4,39,36,41,47,15,45};
        int[] arr1 = {33,22,48,4,60,36,41,47,15,45};
        int[] arr2 = {22,33,48,4};
        //int[] exp = {22,33,48,4,15,36,39,41,45,47};
        int[] exp = {22,33,48,4,15,36,41,45,47,60};
        int[] act = new _1122_RelativeSortArray().relativeSortArray(arr1, arr2);

        assertArrayEquals(exp, act);
    }
}
