package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 298 · Find prime
 * Naive
 * #Math, #Enumerate
 *
 * Output all prime numbers within n.
 *
 * We promise that n is an integer within 100.
 *
 * Example 1:
 * Input：5
 * Output：[2, 3, 5]
 */
public class _0298_Find_Prime {

    public List<Integer> prime(int n) {
        List<Integer> ans = new ArrayList<>();

        // 可以省略 n < 2 的特判, 因为 for 循环不会进

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) { // 这里不用判断 i 是否等于 n, 因为循环保证 i < n
                return false;
            }
        }
        return true;
    }


    /**
     * prime numbers中, 除了 2, 不会再有其他偶数
     */
    public List<Integer> prime_2(int n) {
        List<Integer> ans = new ArrayList<>();

        // 可以省略 n < 2 的特判, 因为 for 循环不会进

        if (n >= 2) { ans.add(2); }

        for (int i = 3; i <= n; i = i + 2) {
            if (isPrime(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean isPrime_2(int n) {
        for (int i = 3; i < n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
