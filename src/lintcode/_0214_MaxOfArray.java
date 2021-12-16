/*
Naive
#Enumerate, #Array

 */
package lintcode;

/**
 * 214 · Max of Array
 *
 * Given an array of float numbers. Return the max value of them.
 *
 * Example 1:
 * Input:  [1.0, 2.1, -3.3]
 * Output: 2.1
 *
 * Example 2:
 * Input:  [1.0, 1.0, -3.3]
 * Output: 1.0
 */
public class _0214_MaxOfArray {

    public float maxOfArray(float[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // 注意, 这里别用 Float.MIN_VALUE

        float max = A[0];
        for (int i = 1; i < A.length; i++) {
            max = Math.max(max, A[i]);
            System.out.println("max = " + max);
            System.out.println("cur = " + A[i]);
        }

        return max;
    }
}
