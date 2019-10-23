package lintcode;

/**
 * 1797. optimalUtilization
 *
 * Give two sorted arrays. To take a number from each of the two arrays,
 *  the sum of the two numbers needs to be less than or equal to k,
 *  and you need to find the index combination with the largest sum
 *  of the two numbers. Returns a pair of indexes containing two arrays.
 *
 * If you have multiple index answers with equal sum of two numbers, you
 *  should choose the index pair with the smallest index of the first array.
 *
 *  1. The sum of the two numbers <= k
 *  2. The sum is the biggest
 *  3. Both array indexes are kept to a minimum
 *
 * Example1
 * A = [1, 4, 6, 9], B = [1, 2, 3, 4], K = 9
 * return [2, 2]
 *
 * Example2:
 * Input:
 * A = [1, 4, 6, 8], B = [1, 2, 3, 5], K = 12
 * Output:
 * [2, 3]
 */
public class _1797_OptimalUtilization {

    /**
     *
     */
    public int[] optimalUtilization(int[] A, int[] B, int K) {
        // write your code here
        int[] result = new int[0];

        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return result;
        }

        int indexA = 0, indexB = 0;
        int currentSum = 0;

        return result;
    }
}
