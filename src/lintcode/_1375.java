package lintcode;

// 1375. Substring With At Least K Distinct Characters
public class _1375 {
	
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
