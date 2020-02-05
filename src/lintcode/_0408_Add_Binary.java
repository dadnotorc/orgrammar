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

    /**
     * 1. 查char的整数值 a.charAt(i) - '0'
     * 2. 算进位用 '/', 算当前位值用 '%'
     * 3. 循环做完了别忘了进位
     * 4. 用StringBuilder, 最后要先reverse
     *
     * time:  O(n)  n为a,b中较长字符串长度 n=Math.max(a.length(), b.length())
     * space: O(1)
     */
    public String addBinary(String a, String b) {

        if (a == null || b == null)
            return a == null ? (b == null ? "" : b) : a;

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int curSum = carry; // 先把进位加进来

            // 注意判断 a和b 可能不等长
            curSum += i < 0 ? 0 : a.charAt(i) - '0';
            curSum += j < 0 ? 0 : b.charAt(j) - '0';

            carry = curSum / 2;
            sb.append(curSum % 2);
        }

        // 别忘了进位
        if (carry != 0)
            sb.append(carry);

        return sb.reverse().toString(); // 要先转向
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
