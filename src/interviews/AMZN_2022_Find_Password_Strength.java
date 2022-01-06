package interviews;

import org.junit.Test;

import java.util.Arrays;
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
     *   | 0 | 1 | 2 | 3 | 4 | 5 - 下标
     *   | b | o | o | b | b | y
     * 0 | 1 | 2 | 2 | 2 | 2 | 3
     *         1 | 1 | 2 | 2 | 3
     *             1 | 2 | 2 | 3
     *                 1 | 1 | 2
     *                     1 | 2
     *                         1
     * 第 i 个字符
     * - 如果第一次 - dp = dp + 1 + i    最后一项 i 表示, 既然当前字符从未出现过, 其之前有 i 个字符, 对应的每个都需要 +1, 即是 +i
     * - 如果遇到过 - dp = dp + 1 + (i - lastIndex - 1)   最后一项代表 当前字符 与上一次出现之间, 有多少别的字符字符, 每个字符都需要 +1, 即是 i - lastIndex - 1
     *
     * 
     */
    public long findPasswordStrength_dp(String password) {
        int ans = 0;
        int dp = 0;

        int[] lastIndex = new int[26]; // 26 个小写字母 最后出现的位置
        Arrays.fill(lastIndex, -1);

        for (int i = 0; i < password.length(); i++) {
            int charIndex = password.charAt(i) - 'a';
            if (lastIndex[charIndex] == -1) { // 没遇见过
                dp += 1 + i;
                lastIndex[charIndex] = i; // 现在遇到了
            } else {
                dp += 1 + (i - lastIndex[charIndex] - 1);
            }

            ans += dp;
        }

        return ans;
    }

    /**
     * g o o d
     * 1 2 2 3    - 到当前字符时, distinct char 共有多少
     *   1 1 2
     *     1 2
     *       1
     *
     * 每次看一层的 distinct chars
     *
     * 可以用 26 位的 int array 取代 hashset, 用于判断是否有遇到过当前字符
     *
     * @param password the password string containing lowercase English letters only
     * @return the strength of the password
     */
    public long findPasswordStrength(String password) {

        int n = password.length();

        int ans = 0;
        for (int i = 0; i < n ; i++) {
            HashSet<Character> set = new HashSet<>(); // 在每层里都需要 reset hashset
            int cur = 0; // 到当前字符时, 有 cur 个 distinct char
            for (int j = i; j < n; j++) {
                char c = password.charAt(j);
                if (!set.contains(c)) { // 是 distinct char
                    cur++;
                    set.add(c);
                }
                // 否则, 就不是 distinct char, 则不递加 cur

                // 但是要注意, 不管是不是 distinct char, 都需要把 cur 加入 ans, 这样才能加到所有值的
                ans += cur;
            }

            // 不能在这里才 ans += cur; 因为这样只加到了最后那位的值
        }

        return ans;
    }

    @Test
    public void test1() {
        String password = "test";
        org.junit.Assert.assertEquals(19, findPasswordStrength(password));
        org.junit.Assert.assertEquals(19, findPasswordStrength_dp(password));

        password = "good";
        org.junit.Assert.assertEquals(16, findPasswordStrength(password));
        org.junit.Assert.assertEquals(16, findPasswordStrength_dp(password));

        password = "bobby";
        org.junit.Assert.assertEquals(26, findPasswordStrength(password));
        org.junit.Assert.assertEquals(26, findPasswordStrength(password));

        password = "boobby";
        org.junit.Assert.assertEquals(37, findPasswordStrength(password));
        org.junit.Assert.assertEquals(37, findPasswordStrength(password));
    }
}
