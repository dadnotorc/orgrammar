package lintcode;

/**
 * 1375. Substring With At Least K Distinct Characters
 * Medium
 *
 * Given a string S with only lowercase characters.
 *
 * Return the number of substrings that contains at least k distinct characters.
 *
 * 10 ≤ length(S) ≤ 1,000,000
 * 1 ≤ k ≤ 26
 *
 * Example 1:
 * Input: S = "abcabcabca", k = 4
 * Output: 0
 * Explanation: There are only three distinct characters in the string.
 *
 * Example 2:
 * Input: S = "abcabcabcabc", k = 3
 * Output: 55
 * Explanation: Any substring whose length is not smaller than 3 contains a, b, c.
 *     For example, there are 10 substrings whose length are 3, "abc", "bca", "cab" ... "abc"
 *     There are 9 substrings whose length are 4, "abca", "bcab", "cabc" ... "cabc"
 *     ...
 *     There is 1 substring whose length is 12, "abcabcabcabc"
 *     So the answer is 1 + 2 + ... + 10 = 55.
 *
 * # two pointers
 */
public class _1375_SubstringWithKDistinctCharacters {
	
	/**
     * @param s: a string
     * @param k: an integer
     * @return: the number of substrings there are that contain at least k distinct characters
     */
    public long kDistinctCharacters(String s, int k) {
        // Write your code here
        
        // note: k distinct chars does NOT have to be a continuous string
        // "abedea" 4 -> abed, abede, abedea, bedea
        // "aabc" 3 -> aabc, abc
        
        int[] cnt = new int[26]; // record how many times each character appears
        int count = 0;           // the size of the sub string with distinct characters
        int l = 0, r = 0;        // left point and right point
        long ans = 0;
        int strLen = s.length();
        
        while (l <= r && l < strLen) {
            while (count < k && r < strLen) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    count++;
                }
                r++;
            }
            if (count == k) {
                ans += strLen - r + 1;
            }
            
            if (cnt[s.charAt(l) - 'a'] == 1) {
                count--;
            }
            cnt[s.charAt(l) - 'a']--;
            l++;
        }
        
        return ans;
    }
}
