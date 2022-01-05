package interviews;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * An algorithm to determine the strength of a password
 *
 * According to this algorithm, the strength of a password consisting of lowercase English letters only, is calculated
 * as the sum of the number of distinct characters present in all possible substring of that password.
 *
 * Given a string password, find the strength of that password.
 *
 * Note: A substring is a contiguous sequence of characters within a string, taken in the same order.
 *
 * Example:
 * password = "good"
 * The possible substrings with distinct character count are:
 * Substring | count of distinct char
 *    g           1
 *     o          1
 *      o         1
 *       d        1
 *    go          2
 *     oo         1
 *      od        2
 *    goo         2
 *     ood        2
 *    good        3
 * Thus, password strength = 1 + 1 + 1 = 1 + 2 + 1 + 2 + 2 + 2 + 3 = 16
 *
 * Constraints:
 * 1 <= length of password < 10 ^ 5
 * The string password consists of lowercase English letters only
 */
public class AMZN_2022_Find_Password_Strength {

    /**
     * g o o d
     * 1 2 2 3    - 到当前字符时, distinct char 共有多少
     *   1 1 2
     *     1 2
     *       1
     *
     * @param password the password string containing lowercase English letters only
     * @return the strength of the password
     */
    public long findPasswordStrength(String password) {

        int n = password.length();

//        // hashmap 记录 <字符, 该字符出现次数>
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            char c = password.charAt(i);
//            map.put(c, map.getOrDefault(c, 0) + 1);
//        }


        int ans = 0;
        for (int i = 0; i < n ; i++) {
            HashSet<Character> set = new HashSet<>();
            int cur = 0;
            for (int j = i; j < n; j++) {
                char c = password.charAt(j);
                if (!set.contains(c)) {
                    cur++;
                    set.add(c);
                }
            }
            ans += cur;
        }

        return ans;
    }

    @Test
    public void test1() {
        String password = "test";
        org.junit.Assert.assertEquals(19, findPasswordStrength(password));
    }
}
