/*
Easy
#Math
Amazon, Microsoft
 */
package lintcode;

import org.junit.Test;

/**
 * 1324. Count Primes
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example 1：
 * Input: n = 2
 * Output: 0
 *
 * Example 2：
 * Input: n = 4
 * Output: 2
 * Explanation：2, 3 are prime number
 *
 * 质数 - 正整数, 只能被1和本身整除
 */
public class _1324_CountPrimes {

    /**
     * 使用长度为n的boolean array, 记录当前下标对应数字是否不为质数, false表示该数为质数, true表示不为质数 (因为array默认值为false)
     * 从2开始到n-1, 如果当前数字i为质数, 则将其小于n的倍数在boolean array中标记 i*j<n
     */
    public int countPrimes(int n) {
        int ans = 0;

        boolean[] notPrime = new boolean[n]; // 0 到 n - 1, 不用考虑n, 因为题目要求是less than n

        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) { // 如果当前i是质数. 从2开始, 2是第一个质数
                ans++;

                for (int j = 2; i * j < n; j++) { // 注意: i*j 要小于n, 而不能小于等于, 否则notPrime[n]会越界
                    notPrime[i * j] = true;
                }
            }
        }

        return ans;
    }


    /**
     * 从2开始到n-1, 查看每个数字是否为质数
     * runtime 更长
     */
    public int countPrimes_2(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) { // 注意题目是less than n, 所以是 < n
            if (isPrime(i)) {
                ans++;
            }
        }
        return ans;
    }

    // 用 i * i 而不是Math.sqrt(n) 因为后者更耗时
    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // 会超时
    private boolean isPrime_too_costly(int n) {
        int m = n / 2;

        if (n == 0 || n == 1) {
            return false;
        } else {
            for (int i = 2; i <= m; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertEquals(4, countPrimes_2(9));
    }
}
