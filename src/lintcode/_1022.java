package lintcode;

/**
 * 1022. Valid Tic-Tac-Toe State
 * Medium
 * Facebook, Microsoft
 *
 * A Tic-Tac-Toe board is given as a string array board.
 * Return true if and only if it is possible to reach this board
 *  position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array, and consists of characters
 *  ' ', 'X', and 'O'. The ' ' character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 * - Players take turns placing characters into empty squares ' '.
 * - The first player always places 'X' characters, while the second player always places 'O' characters.
 * - 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * - The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * - The game also ends if all squares are non-empty.
 * - No more moves can be played if the game is over.
 *
 * - board is a length-3 array of strings, where each string board[i] has length 3.
 * - Each board[i][j] is a character in the set {' ', 'X', 'O'}.
 *
 * Example 1:
 * Input: board = ["O  ", "   ", "   "]
 * Output: false
 * Explanation: The first player always plays "X".
 *
 * Example 2:
 * Input: board = ["XOX", " X ", "   "]
 * Output: false
 * Explanation: Players take turns making moves.
 *
 * Example 3:
 * Input: board = ["XXX", "   ", "OOO"]
 * Output: false
 *
 * Example 4:
 * Input: board = ["XOX", "O O", "XOX"]
 * Output: true
 */
public class _1022 {

    /**
     * @param board 游戏结束时候的状态, 判断能否达成该状态
     */
    public boolean validTicTacToe(String[] board) {

        if (board == null || board.length != 3) {
            return false;
        }

        int xNum = 0, oNum = 0;
        int[][] b = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == ' ') {
                    b[i][j] = 0;
                } else if (board[i].charAt(j) == 'X') {
                    b[i][j] = 1;
                    xNum++;
                } else if (board[i].charAt(j) == 'O') {
                    b[i][j] = -1;
                    oNum++;
                }
            }
        }

        if (xNum < oNum || xNum > oNum + 1) {
            return false;
        }

        int boardState = getState(b[0][0], b[1][1], b[2][2]); //左上至右下
        boardState += getState(b[0][2], b[1][1], b[2][0]); //左下至右上

        for (int i = 0; i <  3; i++) {
            boardState += getState(b[i][0], b[i][1], b[i][2]);
            boardState += getState(b[0][i], b[1][i], b[2][i]);
        }

        /**
         * 当结果为:
         * 0 = 无人取胜 -> true
         * 1/2 并且X比O多1  = X胜 -> true (得2的情况只有一种, XOX-OXO-XOX)
         * -3  并且X跟O同等 = O胜 -> true
         * 其他情况皆为false
         */

        if (boardState == 0) {
            return true;
        } else if ((boardState == 1 || boardState == 2) && xNum == oNum + 1) {
            return true;
        } else if (boardState == -3 && xNum == oNum) {
            return true;
        }
        return false;
    }

    public int getState(int a, int b, int c) {
        int sum = a + b + c;
        if (sum == 3) {
            // input is XXX
            return  1;
        } else if (sum == -3) {
            // input is OOO
            return -3;
        } else {
            return 0;
        }
    }
}
