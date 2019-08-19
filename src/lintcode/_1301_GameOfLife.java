package lintcode;

import org.junit.Test;
import util.helper;

import static org.junit.Assert.*;

/**
 * 1301. Game of Life
 * Medium
 * Apple, Snapchat, Dropbox, Square, Microsoft, Amazon. Uber, Google
 *
 * According to the Wikipedia's article: "The Game of Life, also known simply
 *  as Life, is a cellular automaton devised by the British mathematician
 *  John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1)
 *  or dead (0). Each cell interacts with its eight neighbors
 *  (horizontal, vertical, diagonal) using the following four rules
 *  (taken from the above Wikipedia article):
 *
 * 1. Any live cell with fewer than two live neighbors dies,
 *     as if caused by under-population.
 * 2. Any live cell with two or three live neighbors lives on to the next generation.
 * 3. Any live cell with more than three live neighbors dies,
 *     as if by over-population..
 * 4. Any dead cell with exactly three live neighbors becomes
 *     a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board
 *  given its current state. The next state is created by applying the above
 *  rules simultaneously to every cell in the current state, where births and
 *  deaths occur simultaneously.
 *
 * Example:
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 * Challenge
 * 1. Could you solve it in-place? Remember that the board needs to be updated
 *     at the same time: You cannot update some cells first and then use their
 *     updated values to update other cells.
 * 2. In this question, we represent the board using a 2D array. In principle,
 *     the board is infinite, which would cause problems when the active area
 *     encroaches the border of the array. How would you address these problems?
 */
public class _1301_GameOfLife {

    int[] xs = {-1,-1,-1, 0, 0, 1, 1, 1};
    int[] ys = { 1, 0,-1, 1,-1, 1, 0,-1};

    public void gameOfLife(int[][] board) {
        // Write your code here
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) {
            return;
        }

        int height = board.length;
        int width = board[0].length;

        boolean[][] update = getUpdate(board, width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (board[y][x] == 0 && update[y][x]) {
                    board[y][x] = 1;
                } else if (board[y][x] == 1 && update[y][x]) {
                    board[y][x] = 0;
                }
            }
        }

        return;
    }


    public boolean[][] getUpdate(int[][] board, int width, int height) {

        boolean[][] update = new boolean[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int neighbors = 0;
                for (int i = 0; i < 8; i++) {
                    int neighborX = x+xs[i];
                    int neighborY = y+ys[i];

                    if (isInBound(width, height, neighborX, neighborY)) {
                        neighbors += board[neighborY][neighborX] == 0 ? 0 : 1;
                    }
                }

                if (board[y][x] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        update[y][x] = true;
                    } else {
                        update[y][x] = false;
                    }
                } else {
                    if (neighbors == 3) {
                        update[y][x] = true;
                    } else {
                        update[y][x] = false;
                    }
                }
            }
        }

        return update;
    }

    public boolean isInBound(int width, int height, int x, int y) {

        if (x < 0 || x > width - 1) {
            return false;
        }
        if (y < 0 || y > height - 1) {
            return false;
        }
        return true;
    }

    /* 九章算法 */
    public int countNeighbors(int[][] board, int width, int height, int x, int y) {
        int neighbors = 0;
        for (int i = Math.max(x-1, 0); i <= Math.min(x+1, width-1); i++) { // x within boundary
            for (int j = Math.max(y-1, 0); j <= Math.min(y+1, height-1); j++) { // y within boundary
                // if board[j][i] = 0, do not change
                // if board[j][i] = 1, increment
                neighbors += board[j][i] & 1;
            }
        }

        neighbors -= board[y][x] & 1; // remove self
        return neighbors;
    }

    public void gameOfLife1(int[][] board) {
        // Write your code here
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) {
            return;
        }

        int height = board.length;
        int width = board[0].length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int neighbors = countNeighbors(board, width, height, x, y);
                // 这里要注意！不能实时更新细胞状态，因为会影响到后续细胞的计算
                // 而应当只记录当前细胞在下次更新时，是否会变化
                // 下列注释内为错误代码，因为会实时更新细胞状态
//                if (board[y][x] == 0 && neighbors == 3) {
//                    board[y][x] = 1;
//                } else if (board[y][x] == 1) {
//                    if (neighbors < 2 || neighbors > 3) {
//                        board[y][x] = 0;
//                    }
//                }

                // 使用32位整数的最后两位，以二进制记录细胞更新状态 bitwise operation
                // 0 = 00 表示当前为0，不影响当前计算 “00 & 01” = 0
                //        更新后仍为0。 “00 >>= 1" = 0
                // 2 = 10 表示当前为0，不影响当前计算 “10 & 01” = 0
                //        更新后变为1。 “10 >>= 1" = 1
                // 1 = 01 表示当前为1，不影响当前计算 “01 & 01” = 1
                //        更新后变为0。 “01 >>= 1" = 0
                // 3 = 11 表示当前为1，不影响当前计算 “11 & 01” = 1
                //        更新后仍为1。 “11 >>= 1" = 1
                if (board[y][x] == 1 && neighbors >= 2 && neighbors <= 3) {
                    board[y][x] = 3;
                } else if (board[y][x] == 0 && neighbors == 3) {
                    board[y][x] = 2;
                }
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[y][x] >>= 1;
            }
        }

        return;
    }

    @Test
    public void test1() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] expected = {{0,0,0},{1,0,1},{0,1,1},{0,1,0}};

        new _1301_GameOfLife().gameOfLife(board);

        assertTrue(expected.length == board.length);
        assertTrue(expected[0].length == board[0].length);

        for (int i = 0; i < board.length; i++) {
            assertArrayEquals(expected[i], board[i]);
        }
    }

    @Test
    public void test2() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] expected = {{0,0,0},{1,0,1},{0,1,1},{0,1,0}};

        new _1301_GameOfLife().gameOfLife1(board);

        assertTrue(expected.length == board.length);
        assertTrue(expected[0].length == board[0].length);

        for (int i = 0; i < board.length; i++) {
            helper.log(expected[i]);
            helper.log(board[i]);
            assertArrayEquals(expected[i], board[i]);
        }
    }
}
