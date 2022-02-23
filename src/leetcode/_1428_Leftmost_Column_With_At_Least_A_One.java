package leetcode;

import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 * Medium
 * #Prime
 * Facebook Meta
 *
 * (This problem is an interactive problem.)
 *
 * A binary matrix means that all elements are 0 or 1.
 * For each individual row of the matrix, this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index (0-indexed)
 * with at least a 1 in it. If such index doesn't exist, return -1.
 *
 * You can't access the Binary Matrix directly.
 * You may only access the matrix using a BinaryMatrix interface:
 *
 * - BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
 * - BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
 *
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes you're given the binary matrix mat as input in the following four examples.
 * You will not have access the binary matrix directly.
 *
 * Example 1:
 * Input: mat = [[0,0],[1,1]]
 * 0 0
 * 1 1
 * Output: 0
 *
 * Example 2:
 * Input: mat = [[0,0],[0,1]]
 * 0 0
 * 0 1
 * Output: 1
 *
 * Example 3:
 * Input: mat = [[0,0],[0,0]]
 * 0 0
 * 0 0
 * Output: -1
 *
 * Example 4:
 * Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
 * 0 0 0 1
 * 0 0 1 1
 * 0 1 1 1
 * Output: 1
 *
 * Constraints:
 * - 1 <= mat.length, mat[i].length <= 100
 * - mat[i][j] is either 0 or 1.
 * - mat[i] is sorted in a non-decreasing way.
 */
public class _1428_Leftmost_Column_With_At_Least_A_One {

    /**
     * 比第一种解法优化的地方是, 每行不是再从头找到尾, 而是找到上一个1出现的地方, 缩短每行binary search长度
     */
    public int leftMostColumnWithOne_2(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int n = dimensions.get(0) - 1; // 注意! dimensions是1-indexed, 记得减一
        int m = dimensions.get(1) - 1;
        int ans = -1;

        int rightMostCol = m;

        for (int i = 0; i <= n; i++) {
            int col = findLeftMostOne(binaryMatrix, i, rightMostCol);
            if (col != -1) {
                rightMostCol = col;
                ans = col;
            }
        }

        return ans;
    }


    /**
     * 每一行都用binary search找到最左的1, 比较求得最小值
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int n = dimensions.get(0) - 1; // 注意! dimensions是1-indexed, 记得减一
        int m = dimensions.get(1) - 1;
        int ans = -1;

        for (int i = 0; i <= n; i++) {
            int col = findLeftMostOne(binaryMatrix, i, m);
            if (col != -1) {
                ans = ans == -1 ? col : Math.min(ans, col);
            }
        }

        return ans;
    }

    // use binary search to find the left most 1 on row x, from column 0 to column y
    private int findLeftMostOne(BinaryMatrix matrix, int x, int y) {
        int l = 0, r = y;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            int val = matrix.get(x, mid);
            if (val == 1) {
                r = mid;
            } else {
                l = mid;
            }
        }

        if (matrix.get(x, l) == 1)
            return l;
        if (matrix.get(x, r) == 1)
            return r;
        return -1;
    }
}

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
interface BinaryMatrix {
    // BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
    public int get(int x, int y);

    // BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
    public List<Integer> dimensions();
}