/*
Medium
Recursion, Stack, Divide and Conquer, Non Recursion
Google, Facebook, Salesforce
 */
package lintcode;

import org.junit.Test;

import java.util.Stack;

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

    // 解法一 迭代
    public String expressionExpand_iterative(String s) {
        if (s == null || s.length() == 0)
            return "";

        Stack<Object> stack = new Stack<>();
        int repNum = 0;

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                repNum = repNum * 10 + c - '0';
            } else if (c == '[') {
                stack.push(repNum);
                repNum = 0;
            } else if (c == ']') {
                String tmpStr = popStr(stack);
                int num = (Integer) stack.pop();

                for (int i = 0; i < num; i++) {
                    stack.push(tmpStr);
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }

        return popStr(stack);
    }

    // 获得stack上层里非数字的字符串
    private String popStr(Stack<Object> stack) {
        Stack<String> buffer = new Stack<>();
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            buffer.push((String) stack.pop()); // 第一次转向
        }

        StringBuilder sb = new StringBuilder();
        while (!buffer.isEmpty()) {
            sb.append(buffer.pop()); // 第二次转向
        }
        // 二次转向后, 字符串顺序与原顺势一致
        return sb.toString();
    }

    // todo 解法二 递归
    public String expressionExpand_recursion(String s) {

    }

    @Test
    public void test1() {
        String exp = "abcaaa";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand_iterative("abc3[a]")));
    }

    @Test
    public void test2() {
        String exp = "adadpfpfpfadadpfpfpfadadpfpfpfxyz";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand_iterative("3[2[ad]3[pf]]xyz")));
    }

    @Test
    public void test3() {
        String exp = "xaaaaaaaaaaaay";
        org.junit.Assert.assertTrue(exp.equals(expressionExpand_iterative("x12[a]y")));
    }
}
