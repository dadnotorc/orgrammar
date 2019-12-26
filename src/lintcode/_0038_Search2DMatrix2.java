/*
Medium
Sorted Matrix, Matrix
Amazon, Apple, Google
FAQ
 */
package lintcode;

/**
 * 38. Search a 2D Matrix II
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix,
 * return the occurrence of it.
 *
 * This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - Integers in each column are sorted from up to bottom.
 * - No duplicate integers in each row or column.
 *
 * Example 1:
 * Input:
 * 	[[3,4]]
 * 	target=3
 * Output:1
 *
 * Example 2:
 * Input:
 *     [
 *       [1, 3, 5, 7],
 *       [2, 4, 7, 8],
 *       [3, 5, 9, 10]
 *     ]
 *     target = 3
 * Output:2
 *
 * Challenge
 * - O(m+n) time and O(1) extra space
 */
public class _0038_Search2DMatrix2 {

    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int ans = 0;
        int searchBoundary = matrix[0].length - 1;
        boolean foundTarget = false;

        for (int[] ints : matrix) {

            if (ints[0] >= target) {
                ans += ints[0] == target ? 1 : 0;
                break;
            }

            int l = 0;
            int r = Math.max(0, searchBoundary); // 保证右边 >= 左边
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (ints[mid] == target) {
                    ans++;
                    searchBoundary = mid - 1;
                    foundTarget = true;
                    break;
                } else if (ints[mid] < target) {
                    l = mid;
                } else {
                    r = mid;
                }
            }

            if (!foundTarget) {
                if (ints[l] >= target) {
                    ans += ints[l] == target ? 1 : 0;
                    searchBoundary = l - 1;
                } else {
                    if (ints[r] >= target) {
                        ans += ints[r] == target ? 1 : 0;
                        searchBoundary = l;
                    } else {
                        searchBoundary = r;
                    }
                }
            }
            foundTarget = false;
        }

        return ans;
    }

    // 解法2 - 九章参考
    // https://www.jiuzhang.com/solution/search-a-2d-matrix-ii/
}
