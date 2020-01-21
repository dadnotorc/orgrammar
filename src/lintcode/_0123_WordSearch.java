/*
Medium
DFS, Backtracking
Facebook, Microsoft
 */
package lintcode;

/**
 * 123. Word Search
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example 1:
 *
 * Input：["ABCE","SFCS","ADEE"]，"ABCCED"
 * Output：true
 * Explanation：
 * [
 *      A B C E
 *      S F C S
 *      A D E E
 * ]
 * (0,0)A->(0,1)B->(0,2)C->(1,2)C->(2,2)E->(2,1)D
 * Example 2:
 *
 * Input：["z"]，"z"
 * Output：true
 * Explanation：
 * [ z ]
 * (0,0)z
 */
public class _0123_WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;

        if (word == null || word.length() == 0)
            return true;

        int height = board.length, width = board[0].length;
        boolean[][] isVisited = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (dfs(board, i, j, word, 0, isVisited))
                    return true;
            }
        }

        return false;
    }

    int[] dX = {1, 0, 0, -1};
    int[] dY = {0, 1, -1, 0};

    private boolean dfs(char[][] board, int x, int y, String s, int index, boolean[][] isVisited) {

        if (board[x][y] != s.charAt(index)) // 当前字符不相符
            return false;

        if (index == s.length() - 1) // 当前已到最后一位, 且所有字符都相符
            return true;

        isVisited[x][y] = true; // 注意! 递归前, 先将当前位置标记

        for (int i = 0; i < 4; i++) {
            int neighborX = x + dX[i];
            int neighborY = y + dY[i];

            if (!isValid(neighborX, neighborY, board) || isVisited[neighborX][neighborY]) // 越界 或 已访问过
                continue;

            if (dfs(board, neighborX, neighborY, s, index + 1, isVisited))
                return true;
        }

        isVisited[x][y] = false; // 注意! 递归结束后, 取消标记

        return false;
    }

    private boolean isValid(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

}
