/*
Easy
#Array
Google
FAQ++
 */
package lintcode;

/**
 * 407. Plus One
 *
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 *
 * Example 2:
 * Input: [9,9,9]
 * Output: [1,0,0,0]
 */
public class _0407_PlusOne {

    /**
     * 两种情况:
     * 1. 加法在原数组中某一位结束 (非 most significant digit) -> 修改数组, 进位为0时返回数组. 例如 123 + 1 = 124
     * 2. 加法在most significant digit处才结束 -> 只有一种情况, 就是选数组都是9, 例如 999 + 1 = 1000
     */
    public int[] plusOne(int[] digits) {

        int carry = 1; // 进位从1开始, 因为题目为 +1

        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;

            if (carry == 0) { // 没有进位, 无需后续计算
                return digits;
            }
        }

        // 循环结束, 仍有进位, 只有一种可能, 例如 999 + 1 = 1000
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}
