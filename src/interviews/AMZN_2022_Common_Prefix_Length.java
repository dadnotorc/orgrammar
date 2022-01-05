package interviews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Amazon OA - 2022 年 1月
 *
 * Given a string, split the string into 2 substrings at every possible point.
 * The rightmost substring is a suffix. The beginning of the string is the prefix.
 * Determine the lengths of the common prefix between each suffix and the original string.
 * Sum and return the lengths of the common prefixes.
 * Return an array where each element i is the sum for string i.
 *
 * Example:
 * Consider the only string in the array inputs = ['abcabcd']. Each suffix is compared to the original string
 *
 * Remove to leave suffix | suffix | common prefix | length
 *    ''                  'abcabcd'   'abcabcd'        7
 *    'a'                 'bcabcd'    ''               0
 *    'ab'                'cabcd'     ''               0
 *    'abc'               'abcd'      'abc'            3
 *    'abca'              'bcd'       ''               0
 *    'abcab'             'cd'        ''               0
 *    'abcabc'            'd'         ''               0
 *
 * output = 0
 *
 * Constraints:
 * 1 <= n <= 10
 * 1 <= | inputs[i] | <= 10^5
 * Each inputs[i] contains only letters in the range ascii[a-z]
 */
public class AMZN_2022_Common_Prefix_Length {

    public int[] commonPrefix(String[] inputs) {
        if (inputs == null || inputs.length == 0) {
            return new int[0];
        }

        int[] ans = new int[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            ans[i] = commonPrefix(inputs[i]);
        }

        return ans;
    }

    public int commonPrefix(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = s.length(); // 直接跳过 prefix = "", suffix = 原字符串 这一步
        char[] chars = s.toCharArray();

        // 这个解法也对, 但是没有优化, 需要遍历所有字符
//        for (int i = 1; i < chars.length; i++) {
//            ans += helper(chars, i);
//        }

        /*
        优化 - 不用遍历每个字符, 记录字符串第一个字符出现的位置, 从这些地方遍历即可
         */
        List<Integer> positions = getFirstCharPositions(chars);
        for (int pos : positions) {
            ans += getPrefixLength(chars, pos);
        }

        return ans;
    }

    public int getPrefixLength(char[] chars, int start) {
        int ans = 0, i = 0, j = start;

        while (j < chars.length) {
            if (chars[i++] == chars[j++]) {
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    public List<Integer> getFirstCharPositions(char[] chars) {
        // assume char array has at least 1 element
        char c = chars[0];
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == c) {
                ans.add(i);
            }
        }
        return ans;
    }





    @Test
    public void test1() {
        String[] inputs = {
                "abcabcd",
                "ababaa",
                "aa"
        };

        int[] exp = {10,11,3};

        org.junit.Assert.assertArrayEquals(exp, commonPrefix(inputs));
    }
}
