package leetcode.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class _1111Test {

    @Test
    void test1() {
        String seq = "(()())";
        int[] expected = {0,1,1,1,1,0};
        int[] actual = new _1111().maxDepthAfterSplit(seq);
        assertArrayEquals(expected, actual);
    }

    @Test
    void test2() {
        String seq = "()(())()";
        int[] expected = {0,0,0,1,1,0,1,1};
        int[] actual = new _1111().maxDepthAfterSplit(seq);
        assertArrayEquals(expected, actual);
    }

}