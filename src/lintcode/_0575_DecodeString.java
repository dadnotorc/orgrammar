/*
Medium
Recursion, Stack, Divide and Conquer, Non Recursion
Google, Facebook, Salesforce
 */
package lintcode;

import org.junit.Test;

/**
 * 575. Decode String
 *
 * Given an expression s contains numbers, letters and brackets.
 * Number represents the number of repetitions inside the brackets (can be a
 * string or another expression)．Please expand expression to be a string.
 *
 * Notice
 * - Numbers can only appear in front of “[]”.
 *
 * Example1
 * Input: S = abc3[a]
 * Output: "abcaaa"
 *
 * Example2
 * Input: S = 3[2[ad]3[pf]]xyz
 * Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"
 *
 * Challenge
 * 1. Can you do it without recursion?
 */
public class _0575_DecodeString {

    // todo
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    @Test
    public void test1() {
        String exp = "abcaaa";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand("abc3[a]")));
    }

    @Test
    public void test2() {
        String exp = "adadpfpfpfadadpfpfpfadadpfpfpfxyz";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand("3[2[ad]3[pf]]xyz")));
    }

    @Test
    public void test3() {
        String exp = "xaaaaaaaaaaaay";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand("x12[a]y")));
    }
}
