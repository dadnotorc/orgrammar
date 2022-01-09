/*
Medium
#Sorted Matrix, #Matrix
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

    /**
     * 解法2 - 从左下角开始, 往右/上寻找
     *
     * [[1,2,3,4,5],
     *  [2,3,4,5,6],
     *  [3,4,5,6,7]]
     * 假设寻找3, 第一个在(2,0) -> 之后的寻找就不能在第一列/最后一排里寻找
     * 如果当前点 < target, 在同一排里向后找
     * 如果当前点 > target, 在同一列里向上找
     *
     * 此题与28. Search a 2D Matrix区别在于:
     * 28里规定每排首尾大于前一排的末尾, 所以当把所有排按顺序连在一起时, 整体也是sorted, 所以可以用binary search
     * 此题无此规定, 所以无法对整体做binary search
     *
     * 易错点:
     * 1. 找到某一个target value时, 不能停, 需要继续寻找下一个, 直到走到右上角
     *
     * time:  O(m + n) m为横轴宽度 n为纵轴深度
     */
    public int searchMatrix_2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int ans = 0;
        int x = 0; // x是横轴 范围是 [0, matrix[0].length - 1]
        int y = matrix.length - 1; // y是纵轴 范围是 [0, matrix.length - 1]

        while (x < matrix[0].length && y >= 0) {
            if (matrix[y][x] == target) {
                ans++; // 注意! 这里不能return 因为可能还有未找到的target value
                y--;
                x++;
            } else if (matrix[y][x] < target) { // 要找更大的, 所以在同一排里(横轴)向后找
                x++;
            } else {
                y--; // 要找更小的, 所以在同一列里(纵轴)向上找
            }
        }

        return ans;
    }


    /**
     * 解法1
     */
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
}
