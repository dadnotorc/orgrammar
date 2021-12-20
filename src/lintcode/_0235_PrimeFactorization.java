/*
Easy
#Math
Uber
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 235. Prime Factorization
 *
 * Prime factorize a given integer.
 * - You should sort the factors in ascending order.
 *
 * Example 1:
 * Input: 10
 * Output: [2, 5]
 *
 * Example 2:
 * Input: 660
 * Output: [2, 2, 3, 5, 11]
 */
public class _0235_PrimeFactorization {

    /**
     * - 从小到大遍历[2,up]，若num能够被i整除则循环除以i直到不能被整除，
     *   每次除以i都向答案值数组增加一个i，因为是从小到大遍历，则必定只有质数能被取为因数
     * - up一般设定为sqrt(num)，因为一个数大于其根号的质因数最多只有一个，
     *   那么遍历其根号内的数可以将时间复杂度减小至根号n，若遍历完prime后该数不为1，则其值为最后一个质因数
     *
     * 时间 O(sqrt(n))
     * 空间 O(1)
     */
    public static List<Integer> primeFactorization(int num) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                num /= i;
                ans.add(i);
            }
        }

        if (num != 1) {
            ans.add(num);
        }

        return ans;
    }

    @Test
    public void test1() {
        List<Integer> expected = Arrays.asList(2, 5);
        List<Integer> actual = _0235_PrimeFactorization.primeFactorization(10);

        Integer[] expArray = actual.toArray(new Integer[expected.size()]);
        Integer[] actArray = actual.toArray(new Integer[actual.size()]);

        assertTrue(Arrays.equals(expArray, actArray));
    }

    @Test
    public void test2() {
        List<Integer> expected = Arrays.asList(2, 2, 3, 5, 11);
        List<Integer> actual = _0235_PrimeFactorization.primeFactorization(660);

        Integer[] expArray = actual.toArray(new Integer[expected.size()]);
        Integer[] actArray = actual.toArray(new Integer[actual.size()]);

        assertTrue(Arrays.equals(expArray, actArray));
    }
}
