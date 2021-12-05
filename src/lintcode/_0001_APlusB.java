/*
Naive
#Math

 */
package lintcode;

/**
 * Write a function that add two numbers a and b, and return the answer as an integer(int).
 *
 * There is no need to read data from standard input stream.
 * Both parameters are given in function aplusb, your job is to calculate the sum and return it.
 * -2^31 <= a, b <= 2^31 - 1
 * −2^31 <= a + b < 2^31 - 1
 *
 * Example 1:
 * Input:
 * a = 1
 * b = 2
 * Output:
 * 3
 * Explanation:
 * a + b = 1 + 2 = 3
 *
 * Example 2:
 * Input:
 * a = -1
 * b = 1
 * Output:
 * 0
 * Explanation:
 * a + b = -1 + 1 = 0
 *
 * Challenge
 * Of course you can just return a + b to get accepted.
 * But Can you challenge not do it like that?(You should not use + or any arithmetic operators.)
 */
public class _0001_APlusB {

    /**
     * 利用异或运算来完成，异或运算有一个别名叫做：不进位加法。
     * 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果，然后下面考虑哪些地方要进位，自然是a和b里都是1的地方，
     * a & b就是a和b里都是1的那些位置，a & b << 1 就是进位之后的结果。
     * 所以：a + b = (a ^ b) + (a & b << 1)。令a' = a ^ b, b' = (a & b) << 1。
     * 可以知道，这个过程是在模拟加法的运算过程，进位不可能一直持续，所以b最终会变为0。因此重复做上述操作就可以求得a + b的值。
     */
    public int aplusb(int a, int b) {
        while (b != 0) {
            int c = a ^ b;
            int d = (a & b) << 1;
            a = c;
            b = d;
        }

        return a;
    }
}
