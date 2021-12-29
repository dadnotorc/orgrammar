/*
Easy
#Matrix, #Binary Search
Amazon, Yahoo
FAQ
 */
package lintcode;

/**
 * 28. Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * 	Input:  [[5]],2
 * 	Output: false
 *
 * Example 2:
 * 	Input:  [
 *     [1, 3, 5, 7],
 *     [10, 11, 16, 20],
 *     [23, 30, 34, 50]
 *  ],3
 * 	Output: true
 *
 * Challenge
 * - O(log(n) + log(m)) time
 */
public class _0028_Search2DMatrix {

    /**
     * 类似九章算法 写法稍有不同
     */
    public boolean searchMatrix_1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int l = 0, r = m * n - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int value = matrix[mid / n][mid % n];

            if (value == target) {
                return true;
            } else if (value < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return matrix[l / n][l % n] == target;
    }

    /**
     * 解法3: 九章参考, binary search - 只搜索一次
     * 可以看作是一个有序数组被分成了n段，每段就是一行。因此依然可以二分求解。
     * 对每个数字，根据其下标i，j进行编号. Matrix中共有n*m个数字, 编号为0～n*m-1
     * 相当于是在一个数组中的下标。然后直接像在数组中二分一样来做。取的mid要还原成二位数组中的下标，i = mid/m, j = mid%m
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;

        int l = 0, r = n * m - 1;
        int mid, value;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            value = matrix[mid / m][mid % m];
            if (value == target)
                return true;
            else if (value < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return matrix[l / m][l % m] == target || matrix[r / m][r % m] == target;
    }


    /**
     * 解法2: binary search - 搜索两次, 先搜行, 再搜列
     */
    public boolean searchMatrix_binarysearch(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int l, r, mid, targetRow;
        l = 0;
        r = m - 1;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        // 有可能有一排或者两排: l == r 或者 l + 1 == r
        if (matrix[r][0] == target) {
            return true;
        } else if (matrix[r][0] < target) {
            targetRow = r;
        } else {
            targetRow = l;
        }

        l = 0;
        r = matrix[targetRow].length - 1;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (matrix[targetRow][mid] == target)
                return true;
            else if (matrix[targetRow][mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        if (matrix[targetRow][l] == target || matrix[targetRow][r] == target)
            return true;
        else
            return false;
    }


    /**
     * 解法1: 暴力
     * time:  O(m*n)
     * space: O(1)
     */
    public boolean searchMatrix_bruteforce(int[][] matrix, int target) {
        if (matrix == null)
            return false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target)
                    return true;
            }
        }

        return false;
    }

}
