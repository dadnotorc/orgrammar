/*
Medium
#DFS, #BFS, #Union Find
 */
package leetcode;

/**
 * 130. Surrounded Regions
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border
 * of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not
 * connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if
 * they are adjacent cells connected horizontally or vertically.
 */
public class _0130_SurroundedRegions {

    /**
     * 两次遍历
     * 第一次遍历 - 找出边界上的'O', 使用DFS找出相连的'O', 将其标记 (标记说明其与边界上的'O'相连)
     * 第二次遍历 - 翻转所有未标记的'O'
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        boolean[][] isMarked = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        mark(board, isMarked, i, j);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !isMarked[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void mark(char[][] board, boolean[][] isMarked, int i, int j) {
        // 注意 这里需要判断:
        // 1. 是否越界
        // 2. 当前是否是'X'
        // 3. 如果是'O', 是否已被标记 (说明跟边界的'O'相连)
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] =='X' || isMarked[i][j]) {
            return;
        }

        isMarked[i][j] = true;

        mark(board, isMarked, i + 1, j);
        mark(board, isMarked, i - 1, j);
        mark(board, isMarked, i, j + 1);
        mark(board, isMarked, i, j - 1);
    }
}
