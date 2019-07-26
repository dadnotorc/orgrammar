package lintcode.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class _0421Test {

    @Test
    void test1() {
        String in = "/home/";
        String expected = "/home";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        String in = "/a/./../../c/";
        String expected = "/c";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    void test3() {
        String in = "/../";
        String expected = "/";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    void test4() {
        String in = "/a//b/";
        String expected = "/a/b";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    void test5() {
        String in = "/a/./../";
        String expected = "/";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    void test6() {
        String in = "/a/b/c/../";
        String expected = "/a/b";
        String actual = new _0421().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }
}