package interviews;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Given a string, find the length of the longest substring without
 * repeating characters.
 *
 * Can you find a solution in linear time?
 *
 * Microsoft
 */
public class FindLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) { return 0; }
        int ans = 0;
        int tmp = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                ans = Math.max(ans, tmp);
                //　reset
                set.clear();
                tmp = 0;
            } else {
                tmp++;
                set.add(c);
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        assertEquals(10, lengthOfLongestSubstring("abrkaabcdefghijjxxx"));
    }
}
