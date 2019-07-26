package lintcode.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class _0978Test {

    @Test
    void test1() {
        String s = "1 + 1";
        int actual = new _0978().calculate(s);
        assertEquals(2, actual);
    }

    @Test
    void test2() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int actual = new _0978().calculate(s);
        assertEquals(23, actual);
    }
}