package leetcode;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;

/**
 * 1089. Duplicate Zeros
 * Easy
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of zero,
 *  shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return anything
 *  from your function.
 *
 * Example 1:
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to:
 *  [1,0,0,2,3,0,0,4]
 *
 * Example 2:
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to:
 *  [1,2,3]
 *
 * Note:
 * 1. 1 <= arr.length <= 10000
 * 2. 0 <= arr[i] <= 9
 */
public class _1089_DuplicateZeros {

    // Runtime: 1 ms, Memory Usage: 37.3 MB
    public void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int l1 = arr.length;

        // first scan, from left to right, find the number of 0s in the array
        int zeros = 0;
        for (int i: arr) {
            if (i == 0) {
                zeros++;
            }
        }

        int l2 = l1 + zeros; // this is the length if all 0s are duplicated

        // second scan, from right to left, move the non-0s and duplicate 0s
        int end = l2 - 1;
        for (int i = l1 - 1; i >= 0; i--) {
            if (arr[i] == 0) { // see 0 in arr, then write two 0s (if within arr range)
                if (end < l1) {
                    arr[end] = 0;
                }
                end--;
                if (end < l1) {
                    arr[end] = 0;
                }
            } else { // see non-0 in arr, move to new location (if within range)
                if (end < l1) {
                    arr[end] = arr[i];
                }
            }

            end--;
        }
    }

    // Runtime: 3 ms, Memory Usage: 37.6 MB
    public void duplicateZeros2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int arrLen = arr.length;

        // first scan, find the positions of 0s, save them to a hashset;
        //  also, save non-0s to another array
        HashSet<Integer> zeros = new HashSet<>();
        int[] nonzeros = new int[arrLen];
        int increment = 0, indexNonZeros = 0;
        for (int i = 0; i < arrLen; i++) {
            if (arr[i] == 0) {
                zeros.add(i + increment);
                zeros.add(i + increment + 1);
                increment++;
            } else {
                nonzeros[indexNonZeros++] = arr[i];
            }
        }

        // nonzeros array only contains non-zero values from arr
        // zeros hashset contains the index of 0s, including the duplicated ones

        // second scan, fill in 0s and non-0s
        indexNonZeros = 0;
        for (int i = 0; i < arrLen; i++) {
            if (zeros.contains(i)) {
                arr[i] = 0;
            } else {
                arr[i]= nonzeros[indexNonZeros++];
            }
        }

        return;
    }


    @Test
    public void test1() {
        int[] arr = new int[]{1,0,2,3,0,4,5,0};
        int[] expected = {1,0,0,2,3,0,0,4};
        new _1089_DuplicateZeros().duplicateZeros(arr);
        assertArrayEquals(arr, expected);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1,2,3};
        int[] expected = {1,2,3};
        new _1089_DuplicateZeros().duplicateZeros(arr);
        assertArrayEquals(arr, expected);
    }
}
