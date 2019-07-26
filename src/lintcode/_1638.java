package lintcode;

// 1638. Least Substring
public class _1638 {
	
	/**
     * @param s: the string s
     * @param k: the maximum length of substring
     * @return: return the least number of substring
     */
	public int getAns(String s, int k) {
        // Write your code here
        if (s == null || s.length() == 0 || k <= 0 || s.length() < k) {
            return 0;
        }
        
        int n = s.length(), ans = 1, cnt = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt (i) == s.charAt (i - 1) && cnt < k) {
                cnt++;
            } else {
                ans++;
                cnt = 1;
            }
        }
        return ans;
    }
}
