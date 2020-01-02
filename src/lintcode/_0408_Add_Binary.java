/*
Easy
Binary, String
Facebook
FAQ+
 */
package lintcode;

import org.junit.Test;

/**
 * 408. Add Binary
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * Example 1:
 * Input:
 * a = "0", b = "0"
 * Output:
 * "0"
 *
 * Example 2:
 * Input:
 * a = "11", b = "1"
 * Output:
 * "100"
 */
public class _0408_Add_Binary {

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b ==  null ? "" : b;

        if (b == null || b.length() == 0)
            return a;

        String ansStr = "";
        int carry = 0;

        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int curSum = carry;

            // 注意判断 a和b 可能不等长
            curSum += (i >= 0) ? a.charAt(i) - '0' : 0;
            curSum += (j >= 0) ? b.charAt(j) - '0' : 0;

            ansStr = (curSum % 2) + ansStr;
            carry = curSum / 2;
        }

        if (carry != 0)
            ansStr = carry + ansStr;

        return ansStr;
    }

    @Test
    public void test1() {
        String exp = "0", a = "0", b= "0";
        org.junit.Assert.assertEquals(exp, addBinary(a, b));
    }

    @Test
    public void test2() {
        String exp = "100", a = "11", b= "1";
        org.junit.Assert.assertEquals(exp, addBinary(a, b));
    }
}
