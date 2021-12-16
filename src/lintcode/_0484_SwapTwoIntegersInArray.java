/*
Naive
#Array

 */
package lintcode;

/**
 * 484 · Swap Two Integers in Array
 *
 * Given an array and two indexes, swap the integers on the two indices.
 *
 * Example 1:
 * Input: `[1,2,3,4]` and index1 = `2`, index2 = `3`
 * Output:The array will change to `[1,2,4,3]` after swapping.
 * You don't need return anything, just swap the integers in-place.
 *
 * Example 2:
 * Input: `[1,2,2,2]` and index1 = `0`, index2 = `3`
 * Output:The array will change to `[2,2,2,1]` after swapping.
 * You don't need return anything, just swap the integers in-place.
 */
public class _0484_SwapTwoIntegersInArray {

    public void swapIntegers(int[] A, int index1, int index2) {
        if (A == null || index1 >= A.length || index2 >= A.length || index1 == index2) {
            return;
        }

        int xor = A[index1] ^ A[index2];
        A[index1] ^= xor;
        A[index2] ^= xor;
    }
}
