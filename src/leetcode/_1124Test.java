package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _1124Test {

    @Test
    void  test1() {
        int[] hours = {9,9,6,0,6,6,9};
        int act = new _1124().longestWPI(hours);
        assertEquals(3, act);
    }

    @Test
    void  test2() {
        int[] hours = {6,9,9,6,0,6,6,9};
        int act = new _1124().longestWPI(hours);
        assertEquals(3, act);
    }

    @Test
    void  test3() {
        int[] hours = {6,6,9};
        int act = new _1124().longestWPI(hours);
        assertEquals(1, act);
    }

    @Test
    void  test4() {
        int[] hours = {6,6,6};
        int act = new _1124().longestWPI(hours);
        assertEquals(0, act);
    }

    @Test
    void  test5() {
        int[] hours = {6,9,9};
        int act = new _1124().longestWPI(hours);
        assertEquals(3, act);
    }

    @Test
    void  test6() {
        int[] hours = {6,6,9,6,9,9,9};
        int act = new _1124().longestWPI(hours);
        assertEquals(7, act);
    }
}