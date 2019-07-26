package lintcode.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class _0032Test {

    @Test
    void test1() {
        String source = "abc", target = "ac";
        String expected = "abc";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test2() {
        String source = "adobecodebanc", target = "abc";
        String expected = "banc";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test3() {
        String source = "abc", target = "aa";
        String expected = "";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test4() {
        String source = "a", target = "aa";
        String expected = "";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test5() {
        String source = "daboc", target = "abc";
        String expected = "aboc";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test6() {
        String source = "aboccb", target = "abc";
        String expected = "aboc";
        String actual = new _0032().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    void test7() {
        String source = "aaaaaaaaaaaabbbbbcdd", target = "abcdd";
        String expected = "abbbbbcdd";
        String actual = new _0032().minWindow(source,target);
        System.out.println("=" + actual + "=");
        assertTrue(expected.equals(actual));
    }

    @Test
    void test8() {
        String source = "aabc", target = "abc";
        String expected = "abc";
        String actual = new _0032().minWindow(source,target);
        System.out.println("=" + actual + "=");
        assertTrue(expected.equals(actual));
    }
}