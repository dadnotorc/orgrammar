/*
Easy
Enumeration
LinkedIn
 */
package lintcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 9. Fizz Buzz
 * Given number n. Print number from 1 to n. But:
 * - when number is divided by 3, print "fizz".
 * - when number is divided by 5, print "buzz".
 * - when number is divided by both 3 and 5, print "fizz buzz".
 * - when number can't be divided by either 3 or 5, print the number itself.
 *
 * Example
 * If n = 15, you should return:
 * [
 *   "1", "2", "fizz",
 *   "4", "buzz", "fizz",
 *   "7", "8", "fizz",
 *   "buzz", "11", "fizz",
 *   "13", "14", "fizz buzz"
 * ]
 *
 * If n = 10, you should return:
 * [
 *   "1", "2", "fizz",
 *   "4", "buzz", "fizz",
 *   "7", "8", "fizz",
 *   "buzz"
 * ]
 *
 * Challenge: Can you do it with only one if statement?
 */
public class _0009_FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            //if (i % 3 == 0 && i % 5 == 0) {
            if (i % 15 == 0) {
                ans.add("fizz buzz");
            } else if (i % 3 == 0) {
                ans.add("fizz");
            } else if (i % 5 == 0) {
                ans.add("buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }

        return ans;
    }

    /* ~~~~~ */

    public List<String> fizzBuzz_OneIf(int n) {
        List<String> ans = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            String s = helper(i, 3, "fizz")
                    + helper(i, 15, " ")
                    + helper(i, 5, "buzz");
            ans.add(s.isEmpty() ? String.valueOf(i) : s);
        }

        return ans;
    }

    private String helper(int n, int div, String s) {
        return n % div == 0 ? s : "";
    }

    /* ~~~~~ */

    public List<String> fizzBuzz_OneIf_HashMap(int n) {
        List<String> ans = new LinkedList<>();
        // 用两个hashmap的原因是, 避免误判, 例如:
        // i = 2 -> i % 3 + 3 = 5 -> "buzz" -> 误判
        Map<Integer, String> m1 = new HashMap<>();
        Map<Integer, String> m2 = new HashMap<>();
        m1.put(15, "fizz buzz");
        m1.put(3, "fizz");
        m2.put(5, "buzz");

        for (int i = 1; i <= n; i++) {
            String s = m1.getOrDefault(i % 15 + 15, m1.getOrDefault(i % 3 + 3, "")
                    + m2.getOrDefault(i % 5 + 5, ""));
            ans.add(s.isEmpty() ? String.valueOf(i) : s);
        }

        return ans;
    }


}
