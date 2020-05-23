/*
Medium
#Array, #DP
 */
package leetcode;

/**
 * 1277. Count Square Submatrices with All Ones
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Example 1:
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 *
 * Example 2:
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 * Constraints:
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class _1277_CountSquareSubmatricesWithAllOnes {

    /**
     * dp[][] 1-indexed,
     * dp[i][j] 记录 以matrix[i-1][j-1]为方形右下角, 共有的方块数
     * 遇到'1'时, 此处最大变宽为 1 + min(正上格, 左一格, 左斜上格)
     * 例如
     *               0 0 0 0 0
     *   0 1 1 1     0 0 1 1 1
     *   1 1 1 1  -> 0 1 1 2 2
     *   0 1 1 1     0 0 1 2 3
     *
     * time: O(m * n); space: O(m * n)
     */
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i <= matrix.length; i++) { // 用<= 因为dp是 1-indexed
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = 1 + (Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])));
                    res += dp[i][j];
                }
            }
        }

        return res;
    }


    /**
     * 每遇到'1', 寻找以此点为左上角, 向右下扩展寻找square个数
     */
    public int countSquares(int[][] matrix) {
        int res = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    res += 1 + countSq(matrix, i, j); // +1 是加上自身
                }
            }
        }

        return res;
    }

    private int countSq(int[][] matrix, int x, int y) {
        int size = 1;

        while (x + size < matrix.length && y + size < matrix[0].length) {
            for (int i = x; i <= x + size; i++) { // 注意这里用到 <=
                for (int j = y; j <= y + size; j++) {
                    if (matrix[i][j] == 0) {
                        return size - 1;
                    }
                }
            }

            size++; // 双层for循环做完才能递增变宽, 不能在第一层for循环外就做
        }

        return size - 1;
    }
}
