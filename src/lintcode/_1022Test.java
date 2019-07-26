package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _1022Test {

    @Test
    void test1() {
        String[] b = {"O  ", "   ", "   "};
        boolean actual = new _1022().validTicTacToe(b);
        assertFalse(actual);
    }

    @Test
    void test2() {
        String[] b = {"XOX", " X ", "   "};
        boolean actual = new _1022().validTicTacToe(b);
        assertFalse(actual);
    }

    @Test
    void test3() {
        String[] b = {"XXX", "   ", "OOO"};
        boolean actual = new _1022().validTicTacToe(b);
        assertFalse(actual);
    }

    @Test
    void test4() {
        String[] b = {"XOX", "O O", "XOX"};
        boolean actual = new _1022().validTicTacToe(b);
        assertTrue(actual);
    }


}