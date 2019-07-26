package lintcode;

import org.junit.jupiter.api.Test;
import util.helper;

import static org.junit.jupiter.api.Assertions.*;

public class _1301Test {

    @Test
    void test1() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] expected = {{0,0,0},{1,0,1},{0,1,1},{0,1,0}};

        new _1301().gameOfLife(board);

        assertTrue(expected.length == board.length);
        assertTrue(expected[0].length == board[0].length);

        for (int i = 0; i < board.length; i++) {
            assertArrayEquals(expected[i], board[i]);
        }
    }

    @Test
    void test2() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] expected = {{0,0,0},{1,0,1},{0,1,1},{0,1,0}};

        new _1301().gameOfLife1(board);

        assertTrue(expected.length == board.length);
        assertTrue(expected[0].length == board[0].length);

        for (int i = 0; i < board.length; i++) {
            helper.log(expected[i]);
            helper.log(board[i]);
            assertArrayEquals(expected[i], board[i]);
        }
    }
}
