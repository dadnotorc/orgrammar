/*
Medium
#Recursion, #DFS
Amazon,
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 34. N-Queens II
 *
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 *
 * Example
 * Example 1:
 *
 * Input: n=1
 * Output: 1
 * Explanation:
 * 1:
 * 1
 * Example 2:
 *
 * Input: n=4
 * Output: 2
 * Explanation:
 * 1:
 * 0 0 1 0
 * 1 0 0 0
 * 0 0 0 1
 * 0 1 0 0
 * 2:
 * 0 1 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 1 0
 *
 * 相对33. N-Queens, 此题无需输入棋盘, 所以不需要提供char[][]或者任何数组来记录棋盘
 */
public class _0034_N_Queens2 {

    /**
     * 解法 - 使用memorization检查当前点是否可以放置Queen
     * 每次做完横线/45度角/135度角检查, 如果该点可以放置皇后, 则将3条线上的所有点定义为不可用
     *
     * time:  O(n!) - n!来自the number of nodes in the recursion tree
     * space: O(n^2)
     */
    int totalSolution = 0; // 注意不能将int传入dfs, 因为传递的是数值, 而不是指针, 所以无法更新内容

    public int totalNQueens(int n) {

        boolean[] isQueenOnRow = new boolean[n];
        boolean[] isQueenOnDiagonal1 = new boolean[n + n - 1]; // 45度角线, 棋盘上共同 2n - 1 条斜线
        boolean[] isQueenOnDiagonal2 = new boolean[n + n - 1]; // 135度角线

        dfs(n,0, isQueenOnRow, isQueenOnDiagonal1, isQueenOnDiagonal2);
        return totalSolution;
    }

    private void dfs(int n, int colIndex, boolean[] isQueenOnRow, boolean[] isQueenOnDiagonal1, boolean[] isQueenOnDiagonal2) {
        if (colIndex == n) { // 出口: 已扫完整个棋盘
            // print board
            totalSolution++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // 判断 (i, colIndex) 点是否可以放置Queen
            /* 斜线有 2n-1 条, 下例中各有5条斜线
                   0       1       2
                ___2'______1'______0'__
            2' | (0,0) | (0,1) | (0,2) | 2
            3' | (1,0) | (1,1) | (1,2) | 3
            4' | (2,0) | (2,1) | (2,2) | 4

            假设当前是(1,0)点 -> 45度线为diag1[1], 135度线为diag2[3']
             */
            if (!isQueenOnRow[i] // 横线
                    && !isQueenOnDiagonal1[i + colIndex] // 45度角线
                    && !isQueenOnDiagonal2[i + n - colIndex - 1]) { // 135度角线
                // 当前点可以放置Queen

                isQueenOnRow[i] = true;
                isQueenOnDiagonal1[i + colIndex] = true;
                isQueenOnDiagonal2[i + n - colIndex - 1] = true;

                dfs(n, colIndex + 1, isQueenOnRow, isQueenOnDiagonal1, isQueenOnDiagonal2);

                isQueenOnDiagonal2[i + n - colIndex - 1] = false;
                isQueenOnDiagonal1[i + colIndex] = false;
                isQueenOnRow[i] = false;
            }
        }
    }
}
