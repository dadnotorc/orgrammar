/*
Medium
#Binary, #DP
Amazon
FAQ++
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 411. Gray Code
 *
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the code,
 * find the sequence of gray code.
 * A gray code sequence must begin with 0 and with cover all 2^n integers.
 *
 * Notice
 * - For a given n, a gray code sequence is not uniquely defined.
 * - When n=2, both [0,1,3,2] and [0,2,3,1] are valid gray code sequence according to the above definition.
 *
 * Example 1:
 * Input: 1
 * Output: [0, 1]
 *
 * Example 2:
 * Input: 2
 * Output: [0, 1, 3, 2]
 * Explanation:
 *   0 - 00
 *   1 - 01
 *   3 - 11
 *   2 - 10
 *
 * Challenge
 * - O(2^n) time.
 */
public class _0411_GrayCode {

    /**
     * DP
     * 当n=0时 -> (0)
     * 当n=1时 -> (0,1)
     * 当n=2时 -> (00, 01, 11, 10)
     * 当n=3时 -> (000, 001, 011, 010, 110, 111, 101, 100)
     *
     * 观察n=3时, 结果是在n=2的结果上, 在最左边加1, 并反转顺序
     * 00    000             110
     * 01 -> 001 在此基础上    111
     * 11 -> 011 首位加1并反转 101
     * 10    010             100
     *
     * 易错点:
     * 1. 开始时, 队列只加0, 因为n=0时, 只有队列里只能有0
     */
    public List<Integer> grayCode_DP(int n) {
        List<Integer> res = new ArrayList<>();

        res.add(0); // 这里只加0, 不加1: 因为n可能为0, 0个bits, 答案只有0.

        for (int i = 0; i < n; i++) { // 注意 这里从0开始到n-1
            int size = res.size(); // 先取size, 因为之后会加入新数值
            for (int j = size - 1; j >= 0; j--) { // 从后往前的原因是为了反转
                res.add(res.get(j) | 1 << i); // 用 bit OR 将1加入首位
            }
        }

        return res;
    }


    /**
     * gray code: G(i) = i ^ (i / 2) = i ^ (i >> 1)
     *
     * 一个数字对应的格雷编码的计算方式是:
     * 将其二进制第一位(从高位数)与0异或, 得到的结果为格雷码的第一位
     * 之后依次将原数的第i位与生成的格雷码第i-1位做异或运算, 即可得到格雷码的第i位.
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }

}
