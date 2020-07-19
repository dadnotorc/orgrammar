package leetcode;

/**
 * 190. Reverse Bits
 *
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Example 1:
 * Input: 00000010100101000001111010011100
 * Output: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 *
 * Example 2:
 * Input: 11111111111111111111111111111101
 * Output: 10111111111111111111111111111111
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 *
 * Note:
 * - Note that in some languages such as Java, there is no unsigned integer type. In this case,
 *   both input and output will be given as signed integer type and should not affect your implementation,
 *   as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * - In Java, the compiler represents the signed integers using 2's complement notation.
 *   Therefore, in Example 2 above the input represents the signed integer -3
 *   and the output represents the signed integer -1073741825.
 *
 *
 * Follow up:
 * - If this function is called many times, how would you optimize it?
 */
public class _0190_ReverseBits {

    /**
     * 1. 前16位与后16位互换
     *    abcd efgh ijlk mnop qrst uvwx yzAB CDEF 换成
     *    qrst uvwx yzAB CDEF abcd efgh ijlk mnop
     * 2. 第一与第二个8位互换, 第三与第四个8位互换
     *    qrst uvwx yzAB CDEF abcd efgh ijlk mnop 换成
     *    yzAB CDEF qrst uvwx ijlk mnop abcd efgh
     * 3. 前后4位互换
     *    yzAB CDEF qrst uvwx ijlk mnop abcd efgh 换成
     *    CDEF yzAB uvwx qrst mnop ijlk efgh abcd
     * 4. 前后2位互换
     *    CDEF yzAB uvwx qrst mnop ijlk efgh abcd 换成
     *    EFCD AByz wxuv stqr opmn lkij ghef cdab
     * 5. 前后1位互换
     *    EFCD AByz wxuv stqr opmn lkij ghef cdab 换成
     *    FEDC BAzy xwvu tsrq ponm klji hgfe dcba
     */
    public int reverseBits_4(int n) {
        // 右移用 >>>, 而不是 >>
        n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }


    /**
     *
     * 易错点:
     * 1. 每次对n进行shift时, 使用 >>> 而不是 >>.
     *    - >>> is logical shift right,   11111110 >>> 1 变成 01111111
     *      不管数字是否有正负号, logical shift就是简单的右移, 用0填满左边的空位
     *    - >> is arithmetic shift right, 11111110 >>  1 变成 11111111
     *      最左most significant bit has negative weight, arithmetic shift后, 保留正负号
     * 2. 计算res时, 做到最后一位后, 就不要再左移了, 即只左移31次
     * 3. 更新n时, 需要右移32次, 确保所有32位的每一位都被遍历
     *
     * 另外, Java中, 没有<<<的operator, 因为左移第一次即会最左bit移除
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res |= n & 1;
            if (i != 31) { // 注意! 做完最后一次时, 不要再shift了
                res <<= 1;
            }
            n >>>= 1;
        }
        return res;
    }

    // 以免31次if判断, 然并卵
    public int reverseBits_2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= n & 1;
            n >>>= 1;
        }
        return res;
    }


    // 依旧然并卵
    public int reverseBits_3(int n) {
        int times = 31;
        int res = 0;

        while (times >= 0){
            int lastDigit = n & 1;
            if (lastDigit == 1){
                res |= lastDigit << times;
            }
            times --;
            n = n >> 1;
        }
        return res;
    }
}
