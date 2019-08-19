package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 235. Prime Factorization
 * Easy
 * Uber OA
 *
 * Prime factorize a given integer.
 *
 * You should sort the factors in ascending order.
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

    public static List<Integer> primeFactorization(int num) {
        // write your code here

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
