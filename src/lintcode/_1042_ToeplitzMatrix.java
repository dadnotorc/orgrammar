/*
Easy
#Array
Google
 */
package lintcode;

/**
 * 1042. Toeplitz Matrix
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 *
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 *
 * Notice
 * - matrix will be a 2D array of integers.
 * - matrix will have a number of rows and columns in range [1, 20].
 * - matrix[i][j] will be integers in range [0, 99].
 *
 * Example 1:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: True
 * Explanation:
 * 1234
 * 5123
 * 9512
 *
 * In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]",
 * and in each diagonal all elements are the same, so the answer is True.
 *
 * Example 2:
 * Input: matrix = [[1,2],[2,2]]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 * 左上 - 右下的斜线
 */
public class _1042_ToeplitzMatrix {

    /**
     * 除了最下的row以及最右的column, 遍历其他点, 并与其右下的点比较
     * 跳过了bottom left和top right的点
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return true;

        for (int i = 0; i < matrix.length - 1; i++) { // 到最下方倒数第二层
            for (int j = 0; j < matrix[0].length - 1; j++) { // 到最右边倒数第二列
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 递归 从每一行/每一列的首位开始, 检查当前diagonal line
     *
     * 易错点:
     * 1. 也是到倒数第二行/倒数第二列就结束, 因为比较的是当前点 vs 右下点
     */
    public boolean isToeplitzMatrix_2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return true;

        for (int i = matrix.length - 1; i >= 0; i--) {
            if (!helper(matrix, i, 0)) {
                return false;
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (!helper(matrix, 0, j)) {
                return false;
            }
        }

        return true;
    }

    private boolean helper(int[][] matrix, int i, int j) {
        if (i == matrix.length - 1 || j == matrix[0].length - 1) { // 注意, 到倒数第二行/倒数第二列就结束
            return true;
        }

        if (matrix[i][j] != matrix[i + 1][j + 1]) {
            return false;
        }

        return helper(matrix, i + 1, j + 1);
    }
}
