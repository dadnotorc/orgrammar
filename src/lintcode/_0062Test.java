package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class _0062Test {

    @Test
    void test1() {
        int[] a = {4,5,6,7,8,1,2,3};
        int t = 1;
        int expected = 5;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        int[] a = {4,5,6,7,8,1,2,3};
        int t = 0;
        int expected = -1;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    void test3() {
        int[] a = {4};
        int t = 4;
        int expected = 0;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    void test4() {
        int[] a = {5,4};
        int t = 5;
        int expected = 0;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    void test5() {
        int[] a = {5,4};
        int t = 4;
        int expected = 1;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    @Test
    void test6() {
        int[] a = {2,5};
        int t = 2;
        int expected = 0;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

    void test7() {
        int[] a = {2,5};
        int t = 5;
        int expected = 1;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }

//    @Test
//    void test8() {
//        int[] a = {3,2,1,8,7,6,5,4};
//        int t = 0;
//        int expected = -1;
//        int actual = new _0062().search(a, t);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void test9() {
//        int[] a = {3,2,1,8,7,6,5,4};
//        int t = 1;
//        int expected = 2;
//        int actual = new _0062().search(a, t);
//        assertEquals(expected, actual);
//    }

    @Test
    void test10() {
        int[] a = {6,8,9,1,3,5};
        int t = 5;
        int expected = 5;
        int actual = new _0062().search(a, t);
        assertEquals(expected, actual);
    }
}