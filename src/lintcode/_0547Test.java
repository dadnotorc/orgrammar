package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class _0547Test {

    @Test
    void test1() {
        int[] a = {1,2,2,1};
        int[] b = {2,2};
        int[] expected = {2};
        int[] actual = new _0547().intersection(a, b);
        int[] actual1 = new _0547().intersection1(a, b);
        int[] actual2 = new _0547().intersection2(a, b);
        int[] actual3 = new _0547().intersection3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }

    @Test
    void test2() {
        int[] a = {1,2};
        int[] b = {2};
        int[] expected = {2};
        int[] actual = new _0547().intersection(a, b);
        int[] actual1 = new _0547().intersection1(a, b);
        int[] actual2 = new _0547().intersection2(a, b);
        int[] actual3 = new _0547().intersection3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }

    @Test
    void test3() {
        int[] a = {7,3,5,9,7,3};
        int[] b = {5,3,5,8};
        int[] expected = {3,5};
        int[] actual = new _0547().intersection(a, b);
        int[] actual1 = new _0547().intersection1(a, b);
        int[] actual2 = new _0547().intersection2(a, b);
        int[] actual3 = new _0547().intersection3(a, b);
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, actual1);
        assertArrayEquals(expected, actual2);
        assertArrayEquals(expected, actual3);
    }
}