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

    class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 易错点:
     * 1. exist()中的双层for循环里, 不能直接return dfs(), 因为此时遍历可能仍未做完 (会错误地提前返回false)
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;

        if (word == null || word.length() == 0)
            return true;

        int n = board.length, m = board[0].length;
        boolean[][] isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 不可以直接 return dfs(), 因为遍历仍未做完
                if (dfs(board, new Coord(i, j), word, 0, isVisited))
                    return true;
            }
        }

        return false;
    }

    int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private boolean dfs(char[][] board, Coord coord, String s, int index, boolean[][] isVisited) {

        if (board[coord.x][coord.y] != s.charAt(index)) // 当前字符不相符
            return false;

        if (index == s.length() - 1) // -1 是因为当前在最后一位, 且上一行判断确定了所有字符都相符
            return true;

        isVisited[coord.x][coord.y] = true; // 注意! 递归前, 先将当前位置标记

        for (int i = 0; i < 4; i++) {
            Coord neighbor = new Coord(coord.x + direction[i][0], coord.y + direction[i][1]);

            if (!isValid(neighbor, board) || isVisited[neighbor.x][neighbor.y]) // 越界 或 已访问过
                continue;

            // 不可以直接 return dfs(), 因为遍历仍未做完
            if (dfs(board, neighbor, s, index + 1, isVisited))
                return true;
        }

        isVisited[coord.x][coord.y] = false; // 注意! 递归结束后, 取消标记

        return false;
    }

    private boolean isValid(Coord coord, char[][] board) {
        return coord.x >= 0 && coord.x < board.length
                && coord.y >= 0 && coord.y < board[0].length;
    }

}
