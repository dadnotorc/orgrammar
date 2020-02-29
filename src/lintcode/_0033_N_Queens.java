/*
Medium
#Recursion, #DFS
Amazon
 */
package lintcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 33. N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other(Any two queens can't be in the
 * same row, column, diagonal line).
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' each indicate a queen and an empty space respectively.
 *
 * Example 1:
 * Input:1
 * Output:
 *    [["Q"]]
 *
 * Example 2:
 * Input:4
 * Output:
 * [
 *   // Solution 1
 *   [".Q..",
 *    "...Q",
 *    "Q...",
 *    "..Q."
 *   ],
 *   // Solution 2
 *   ["..Q.",
 *    "Q...",
 *    "...Q",
 *    ".Q.."
 *   ]
 * ]
 *
 * Challenge
 * - Can you do it without recursion?
 */
public class _0033_N_Queens {

    /**
     * 1. 创建一组 n×n 的char[][], 用于存储棋盘内容, 初始值为'.'
     * 2. 将上述数组带入dfs, 同时带入column index (从0)开始, 以及答案需要的List<List<String>>
     *    a) 定义: 找出所有满足要求的棋盘
     *    b) 出口: 如果当前column index已达最大值, 将数组转化成List<String>, 加入答案中
     *    b) 拆解: 考虑column index纵线上的每一个点, 如果符合下列任何一条要求, 说明此点无法放置皇后, 则跳过.
     *             否则, 将其值变为'Q', 并带入下层递归.
     *             别忘了递归返回时, 将其值恢复成'.'  (假设原点为(x,y), 新点为(i,j)
     *       * 两点在同一横线 (same row index)
     *       * 两点在45度线上 (左下-右上) (x + y == i + j)
     *       * 两点在135度线上 (左上-右下) (x + j == y + i)
     * 3. 将char[][]逐排转化成String加入答案
     *
     * time:  O(n^2 * n!) - n^2 来自isValid(), n!来自the number of nodes in the recursion tree
     * space: O(n^2)
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];

        // init board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> ans = new ArrayList<>();
        dfs(board, 0, ans);
        return ans;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> ans) {
        if (colIndex == board.length) {
            List<String> oneSolution = printBoard(board);
            ans.add(oneSolution);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, ans);
                board[i][colIndex] = '.';
            }
        }
    }

    // 棋盘上小于 y 的左半边已有皇后, 判断(x,y)点是否再放一枚皇后
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) { // 检查 y 线以左的所有点
                if (board[i][j] == 'Q' &&
                        ((i == x) || // 同条横线上
                        (x + y == i + j) || // 45度线上
                        (x + j == y + i))) { // 135度线上
                    return false;
                }
            }
        }
        return true;
    }

    // 将 board 逐行转成String, 加入List中
    private List<String> printBoard(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] curRow : board) {
            String tmp = new String(curRow);
            solution.add(tmp);
        }

        return solution;
    }



    /**
     * 解法2 - 使用memorization简化isValid()
     * 每次做完横线/45度角/135度角检查, 如果该点可以放置皇后, 则将3条线上的所有点定义为不可用
     *
     * time:  O(n!) - n!来自the number of nodes in the recursion tree
     * space: O(n^2)
     */
    public List<List<String>> solveNQueens_memorization(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> ans = new ArrayList<>();

        boolean[] isQueenOnRow = new boolean[n];
        boolean[] isQueenOnDiagonal1 = new boolean[n + n - 1]; // 45度角线, 棋盘上共同 2n - 1 条斜线
        boolean[] isQueenOnDiagonal2 = new boolean[n + n - 1]; // 135度角线

        dfs(board, 0, ans, isQueenOnRow, isQueenOnDiagonal1, isQueenOnDiagonal2);
        return ans;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> ans,
                     boolean[] isQueenOnRow, boolean[] isQueenOnDiagonal1, boolean[] isQueenOnDiagonal2) {
        if (colIndex == board.length) { // 出口: 已扫完整个棋盘
            // print board
            List<String> curRow = new ArrayList<>();
            for (char[] charArray : board) {
                curRow.add(new String(charArray));
            }

            ans.add(curRow);
            return;
        }

        for (int i = 0; i < board.length; i++) {
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
                    && !isQueenOnDiagonal2[i + board.length - colIndex - 1]) { // 135度角线
                // 当前点可以放置Queen

                isQueenOnRow[i] = true;
                isQueenOnDiagonal1[i + colIndex] = true;
                isQueenOnDiagonal2[i + board.length - colIndex - 1] = true;
                board[i][colIndex] = 'Q';

                dfs(board, colIndex + 1, ans, isQueenOnRow, isQueenOnDiagonal1, isQueenOnDiagonal2);

                board[i][colIndex] = '.';
                isQueenOnDiagonal2[i + board.length - colIndex - 1] = false;
                isQueenOnDiagonal1[i + colIndex] = false;
                isQueenOnRow[i] = false;
            }
        }
    }


    /**
     * TODO 解法3 - how to solve without using recursion?
     */
}

