/*
Medium
#Stack, #Greedy
 */
package leetcode;

/**
 * 402. Remove K Digits
 *
 * Given a non-negative integer num represented as a string,
 * remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 *
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class _0402_RemoveKDigits {

    /**
     * 从左到右读取每个数字, 如果当前位比前一位小, 用当前位数字覆盖前一位.
     * 例如num=2331,k=2:
     * 一直读取到233, 当前位都不比前一位大, 所以直接写入数组 -> 233
     * 下一位1, 在数组中往前跳过大于1的数组, 最多跳过k位, 用1覆盖 -> 213
     * 输出时, 只输出从最左边非0位开始, 长度为2位的数字 -> 21
     */
    public String removeKdigits(String num, int k) {
        // 去掉k个数字后, 剩余数字的最大值. 例如 num=10203, k=2, 去掉2个数字后, 最多还剩3位 003
        int digitsLeft = num.length() - k;

        char[] arr = new char[num.length()];
        int index = 0; // 指向arr中当前为空的下标, index之前的下标均已填入数字

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            // 注意这里要保证 k > 0
            while (index > 0 && arr[index - 1] > c && k > 0) { // 用while找出在arr中已写入的比c大的数字, 将其覆盖
                index--; // 往前移动下标 -> 覆盖已写入的数字
                k--;
            }

            arr[index++] = c;
        }
        // 10203 去掉2位后, arr = 003

        index = 0;

        // 跳过开头的0. index < digitsLeft 的原因是只看最多digitiLeft位数字
        while (index < digitsLeft && arr[index] == '0')
            index++;

        if (index == digitsLeft)
            return "0";

        return new String(arr, index, digitsLeft - index);
    }
}
