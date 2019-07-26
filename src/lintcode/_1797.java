package lintcode;

/**
 * 1797. optimalUtilization
 * Give two sorted arrays. To take a number from each of the two arrays,
 *  the sum of the two numbers needs to be less than or equal to k,
 *  and you need to find the index combination with the largest sum of the two numbers.
 *  Returns a pair of indexes containing two arrays.
 * If you have multiple index answers with equal sum of two numbers,
 *  you should choose the index pair with the smallest index of the first array.
 *
 *  1. The sum of the two numbers <= k
 *  2. The sum is the biggest
 *  3. Both array indexes are kept to a minimum
 *
 */
public class _1797 {

    /**
     * @param A: a integer sorted array
     * @param B: a integer sorted array
     * @param K: a integer
     * @return : return a pair of index
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
