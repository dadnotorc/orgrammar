/*
Medium
#Array, #Binary Search
 */
package leetcode;

/**
 * 74. Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 *
 * Example 2:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */
public class _0074_Search2DMatrix {

    /**
     * 使用Binary search, 将整体当做一个sorted array.
     * 每个点的值 = matrix[index / m][index % m], m为column总数 = matrix[0].length
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = n * m - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            int value = matrix[mid / m][mid % m];
            if (value == target) {
                return true;
            } else if (value < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (matrix[l / m][l % m] == target || matrix[r / m][r % m] == target)
            return true;

        return false;
    }
}
