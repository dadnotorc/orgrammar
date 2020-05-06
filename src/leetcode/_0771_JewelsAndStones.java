/*
Easy
#Hash Table
 */
package leetcode;

import java.util.HashSet;

/**
 * 771. Jewels and Stones
 *
 * You're given strings J representing the types of stones that are jewels,
 * and S representing the stones you have. Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters.
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 *
 * Example 2:
 * Input: J = "z", S = "ZZ"
 * Output: 0
 *
 * Note:
 * S and J will consist of letters and have length at most 50.
 * The characters in J are distinct.
 */
public class _0771_JewelsAndStones {

    /**
     * 比使用HashSet更快
     */
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null)
            return 0;

        int ans = 0;
        for (int i = 0; i < J.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                if (J.charAt(i) == S.charAt(j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * 使用HashSet记录jewel, 再遍历S对比stone与jewel
     */
    public int numJewelsInStones_2(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < J.length(); i++){
            set.add(J.charAt(i));
        }

        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))) {
                ans++;
            }
        }

        return ans;
    }
}
