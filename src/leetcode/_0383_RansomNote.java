/*
Easy
#String
 */
package leetcode;

/**
 * 383. Ransom Note
 *
 * Given an arbitrary ransom note string and another string containing letters from
 * all the magazines, write a function that will return true if the ransom note can
 * be constructed from the magazines; otherwise, it will return false.
 *
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 * Note:
 * You may assume that both strings contain only lowercase letters.
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public class _0383_RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0)
            return true;

        if (magazine == null || magazine.length() == 0)
            return false;

        int[] counts = new int[26]; // 26个小写字母

        for (int i = 0; i < magazine.length(); i++) {
            char m = magazine.charAt(i);
            counts[m - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char r = ransomNote.charAt(i);

            if (counts[r - 'a'] == 0) {
                return false;
            } else {
                counts[r - 'a']--;
            }

            //这里可以写成
            /*
            if (--counts[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
             */
        }

        return true;
    }


    public boolean canConstruct_2(String ransomNote, String magazine) {

        int[] counts = new int[26]; // 26个小写字母

        for (int i = 0; i < magazine.length(); i++) {
            counts[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (--counts[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
