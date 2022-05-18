package lintcode;

import org.junit.Test;

/**
 * 408. Add Binary
 * Easy
 * Binary, String
 * Facebook
 * FAQ+
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
     * 先将 a 和 b 转化成十进制数，求和后再转化为二进制数。
     *
     * 如果 a 的位数是 n，b 的位数为 m，这个算法的渐进时间复杂度为 O(n + m)。
     * 但是这里非常简单的实现基于 Python 和 Java 本身的高精度功能，在其他的语言中可能并不适用，
     * 并且在 Java 中：
     * - 如果字符串超过 33 位，不能转化为 Integer
     * - 如果字符串超过 65 位，不能转化为 Long
     * - 如果字符串超过 500000001 位，不能转化为 BigInteger
     * 因此，为了适用于长度较大的字符串计算，我们应该使用更加健壮的算法。
     */
    public String addBinary_2(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }


    /**
     * 1. 查char的整数值 a.charAt(i) - '0'
     * 2. 算进位用 '/', 算当前位值用 '%'
     * 3. 循环做完了别忘了进位
     * 4. 用StringBuilder, 最后要先reverse
     *
     * time:  O(n)  n为 a,b 中较长字符串长度 n = Math.max(a.length(), b.length())
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
