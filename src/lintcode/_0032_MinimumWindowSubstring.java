package lintcode;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

import static org.junit.Assert.assertTrue;


/**
 * 32. Minimum Window Substring
 * Medium
 * LinkedIn, Snapchat, Facebook, Uber
 *
 * Given two strings source and target.  Return the minimum
 *  substring of source which contains each char of target.
 *
 * 1. If there is no answer, return "".
 * 2. You are guaranteed that the answer is unique.
 * 3. target may contain duplicate char, while the answer need to
 *     contain at least the same number of that char.
 *
 * Example 1:
 * Input: source = "abc", target = "ac"
 * Output: "abc"
 *
 * Example 2:
 * Input: source = "adobecodebanc", target = "abc"
 * Output: "banc"
 * Explanation: "banc" is the minimum substring of source string
 *  which contains each char of target "abc".
 *
 * Example 3:
 * Input: source = "abc", target = "aa"
 * Output: ""
 * Explanation: No substring contains two 'a'.
 *
 * Challenge
 * - O(n) time
 */
public class _0032_MinimumWindowSubstring {

    /**
     * 使用array, 速度较快
     * 使用array记录target中每个字符出现次数,
     * 右指针遍历时, 更新array中所有出现字符的次数 (都-1)
     * 当target中所有字符都找齐 (validCharLen >= target.length()), 开始挪动左指针
     * 左指针跳过所有无效字符, 直到最短字符串找到时结束, 每次左指针跳动, 更新答案长度
     */
    public String minWindow(String source, String target) {
        if (source == null || target == null) {
            return "";
        }

        int validCharLen = 0;
        //+1的原因是避免错误的判断subStr跟source一样的情况
        //例如,s="abc" t="ac", subStr="abc"
        String subStr = "";
        int subStrLen = source.length() + 1;

        int[] counts = new int[256];
        // 将target中每个字符出现次数存入array
        for (char c : target.toCharArray()) {
            counts[c]++;
        }

        int left = 0;
        for (int right = 0; right < source.length(); right++) {
            char rc = source.charAt(right);

            /**
             * validCharLen记录当前子字符串中, 有效字符长度
             * 例如, s=abbc,t=abc, 因为子字符串必须包含abc,重复出现的b不记为有效字符
             *  所有有效长度为3, 而不是4
             *
             * 之后将所有遇到的字符都-1, target未出现的字符在数组中变为负数
             */
            if (counts[rc] > 0) {
                validCharLen++;
            }
            counts[rc]--;

            // 找到target中所有字符后, 左指针跳过无效字符
            while(validCharLen >= target.length()) {
                //先记录子字符串, 取较短的
                if (subStrLen > right - left + 1) {
                    subStr = source.substring(left, right + 1);
                    subStrLen = subStr.length();
                }

                char lc = source.charAt(left);
                counts[lc]++;
                if (counts[lc] > 0) {
                    //左指针找到target中的字符, 跳过第一个
                    //例如, abocxx, 左指针跳过a指去b时,有效字符长度从3变为2, 然后停止移动
                    validCharLen--;
                }
                left++;
            }
        }

        return subStr;
    }

    /**
     * 使用hashtable, 速度比使用array较慢
     */
    public String minWindow_hashtable(String source, String target) {
        // write your code here
        String ans = null;

        if (source == null || source.length() == 0 ||
                target == null || target.length() == 0 ||
                source.length() < target.length()) {
            return "";
        }

        /**
         * 将target中每个字符作为key存入hashtable, 对应value为该字符出现次数
         * 该value也为在source中需要找到该字符的次数
         */
        Hashtable<Character, Integer> tb = new Hashtable<>();
        for (char c : target.toCharArray()) {
//            if (tb.containsKey(c)) {
//                tb.put(c, tb.get(c) + 1);
//            } else {
//                tb.put(c, 1);
//            }
            tb.put(c, tb.containsKey(c) ? tb.get(c) + 1 : 1);
        }

        int start = 0;
        for (int i = 0; i < source.length(); i++) {
            // i为右指针-先移动, start为左指针-后移动
            char startChar = source.charAt(start);
            char endChar = source.charAt(i);
            if (tb.containsKey(endChar)) {
                //在s中找到t包含的字符, 将其所需次数 -1
                //可能出现负数, 表示当前子字符串中,该字符超过需要出现的次数
                tb.put(endChar, tb.get(endChar) - 1);
            }

            if (foundEnd(tb)) {
                //先移动左指针, 去除多余的字符
                //并将左指针所指字符加回hashtable中, 左指针向右移动到下一位符合要求的字符串

                while (!tb.containsKey(startChar) ||
                        (tb.containsKey(startChar) && tb.get(startChar) < 0)) {
                    if (tb.containsKey(startChar)) {
                        tb.put(startChar, tb.get(startChar) + 1);
                    }
                    //start++;
                    startChar = source.charAt(++start);
                }

                String subStr = source.substring(start, i + 1);
                if (ans == null) {
                    ans = subStr;
                } else if (subStr.length() < ans.length()) {
                    // 记录最短的子字符串
                    ans = subStr;
                }
            }
        }

        return ans == null ? "" : ans;
    }

    public boolean foundEnd(Hashtable<Character, Integer> tb) {
        /**
         * 注意! 不可以用所有值求和是否等于0的方式来判读, 因为可能出现 +1,-1抵消的情况
         */
        Enumeration<Integer> e = tb.elements();
        while (e.hasMoreElements()){
            if (e.nextElement() > 0) { // 注意用 > 0 而不是 != 0
                return false;
            }
        }

        return true;
    }

    @Test
    public void test1() {
        String source = "abc", target = "ac";
        String expected = "abc";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test2() {
        String source = "adobecodebanc", target = "abc";
        String expected = "banc";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test3() {
        String source = "abc", target = "aa";
        String expected = "";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test4() {
        String source = "a", target = "aa";
        String expected = "";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test5() {
        String source = "daboc", target = "abc";
        String expected = "aboc";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test6() {
        String source = "aboccb", target = "abc";
        String expected = "aboc";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test7() {
        String source = "aaaaaaaaaaaabbbbbcdd", target = "abcdd";
        String expected = "abbbbbcdd";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        System.out.println("=" + actual + "=");
        assertTrue(expected.equals(actual));
    }

    @Test
    public void test8() {
        String source = "aabc", target = "abc";
        String expected = "abc";
        String actual = new _0032_MinimumWindowSubstring().minWindow(source,target);
        System.out.println("=" + actual + "=");
        assertTrue(expected.equals(actual));
    }
}
