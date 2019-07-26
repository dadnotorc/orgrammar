package lintcode;

import java.util.HashMap;
import java.util.HashSet;

// 1639. K-Substring with K different characters
public class _1639 {

    /**
     * @param stringIn: The original string.
     * @param K:        The length of substrings.
     * @return: return the count of substring of length K and exactly K distinct
     *          characters.
     */
    public int KSubstring(String stringIn, int K) {
        // Write your code here
        if (stringIn == null || stringIn.length() == 0 || K <= 0 || stringIn.length() < K) {
            return 0;
        }

        HashMap<Character, Integer> charMap = new HashMap<>();
        HashSet<String> resultSet = new HashSet<>();

        int strLen = stringIn.length();
        int right = 0;

        for (int left = 0; right < strLen; left++) {
            while (right < strLen && charMap.size() < K) {
                char c = stringIn.charAt(right);
                if (charMap.containsKey(c)) {
                    break;
                }

                charMap.put(c, 1);
                right++;

                if (charMap.size() == K) {
                    resultSet.add(stringIn.substring(left, right));

                }
            }
            charMap.remove(stringIn.charAt(left));
        }

        // debug
        /*
         * Iterator<String> itr = resultSet.iterator(); while (itr.hasNext()) {
         * System.out.println(itr.next()); }
         */

        return resultSet.size();
    }

}