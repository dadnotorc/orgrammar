/*
Naive
#Math, #Enumerate

 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 298 · Find prime
 *
 * Output all prime numbers within n.
 *
 * We promise that n is an integer within 100.
 *
 * Example 1:
 * Input：5
 * Output：[2, 3, 5]
 */
public class _0298_FindPrime {

    public List<Integer> prime(int n) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
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

        if (n > 2) {
            ans.add(2);
        }

        for (int i = 3; i <= n; i = i + 2) {
            if (isPrime(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean isPrime_2(int n) {
        int i = 3;
        while (i < n) {
            if (n % i == 0) {
                return false;
            }
            i += 2;
        }
        return true;
    }

}
