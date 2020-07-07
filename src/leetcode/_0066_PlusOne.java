/*
Easy
#Array
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 66. Plus One
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 * Example 2:
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class _0066_PlusOne {


    /**
     * 从后往前, 如果当前位+1后无需进位(原数<9), 将其+1后返回即可, 无需考虑之前的数字
     * 如果需要进位, 将当前位定为0, 然后继续检查前一位
     *
     * 如果原数组中所有数字均为9, +1后, 长度增加一位, 所有数字均为0, 除了首位为1
     */
    public int[] plusOne_2(int[] digits) {
        int[] res = new int[digits.length];
        System.arraycopy(digits, 0, res, 0, digits.length);

        for (int i = digits.length - 1; i >= 0; i--) {
            if (res[i] < 9) {
                res[i]++;
                return res;
            } else {
                res[i] = 0;
            }
        }

        // 上述for循环结束后, 如果仍未return, 说明原数组中数字均为9, +1后, 所有数字均为0, 除了首位为1
        res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }




    /**
     * 1. 将数组转成list进行计算.
     *    从数组最后一位开始, 将数值将入list首位
     * 2. 将数组转回数组返回
     *
     * 易错点:
     * 1. 将carryOver初始值定为1, 就不用单独计算最后一位
     * 2. 所有数字计算完, 别忘了检查carryOver是否为0
     */
    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int carryOver = 1; // 将carryOver设为1, 就无需单独将最后一位+1
        for (int i = digits.length - 1; i >= 0; i--) {
            int tmp = digits[i] + carryOver;
            list.add(0, tmp % 10);
            carryOver = tmp / 10;
        }
        if (carryOver != 0) {
            list.add(0, carryOver);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
