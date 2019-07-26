package lintcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class _0669Test {

    @Test
    void test1() {
        int[] coins = {1,2,5};
        int amount = 11;
        int expected = 3;
        int actual = new _0669().coinChange(coins, amount);
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        int[] coins = {2};
        int amount = 3;
        int expected = -1;
        int actual = new _0669().coinChange(coins, amount);
        assertEquals(expected, actual);
    }

}