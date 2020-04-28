/*
Medium
#DP
 */
package leetcode;

/**
 * 221. Maximal Square
 *
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Output: 4
 */
public class _0221_MaximalSquare {

    /**
     * dp[][] 1-indexed, 记录在matrix[i-1][j-1]处遇到的最大square的宽度
     * 遇到'1'时, 此处最大变宽为 1 + min(正上格, 左一格, 左斜上格)
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int maxWidth = 0;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + (Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])));
                    maxWidth = Math.max(maxWidth, dp[i][j]);
                }
            }
        }

        return maxWidth * maxWidth;
    }


    /**
     * 每遇到'1', 寻找以此点为左上角, 向右下扩展寻找最大的square边宽
     */
    public int maximalSquare_2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int maxSize = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int size = getSize(matrix, i, j);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        return maxSize * maxSize;
    }

    private int getSize(char[][] matrix, int x, int y) {
        int size = 1;

        while (x + size < matrix.length && y + size < matrix[0].length) {
            for (int i = x; i <= x + size; i++) { // 注意这里用到 <=
                for (int j = y; j <= y + size; j++) {
                    if (matrix[i][j] == '0') {
                        return size;
                    }
                }
            }

            size++; // 双层for循环做完才能递增变宽, 不能在第一层for循环外就做
        }

        return size;
    }
}
