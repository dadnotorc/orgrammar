/*
Easy
#Mathematics
Airbnb, Google
 */
package lintcode;

/**
 * 655. Add Strings
 *
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Notice
 * - The length of both num1 and num2 is < 5100.
 * - Both num1 and num2 contains only digits 0-9.
 * - Both num1 and num2 does not contain any leading zero.
 * - You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Input : num1 = "123", num2 = "45"
 * Output : "168"
 */
public class _0655_AddStrings {

    /**
     * 从各自字符串末尾向前移动相加, 记录进位
     *
     * 易错点:
     * 1. 别忘了移动坐标
     * 2. while循环结束后, 别忘了检查进位是否为0
     * 3. 因为使用StringBuilder, 生成的字符串返回前需要转向
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int carry = 0;

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + carry;

            sb.append(sum % 10);
            carry = sum / 10;

            // 别忘了坐标--
            i--;
            j--;
        }

        // 别忘了carry
        if (carry != 0)
            sb.append(carry);

        // 别忘了转向
        return sb.reverse().toString();
    }
}
