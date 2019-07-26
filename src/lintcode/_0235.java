package lintcode;

import java.util.ArrayList;
import java.util.List;

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
public class _0235 {

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
}
