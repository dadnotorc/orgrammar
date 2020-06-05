/*
Easy
#String, #Two Pointers
 */
package leetcode;

/**
 * 344. Reverse String
 *
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 * Example 1:
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class _0344_ReverseString {

    /**
     * 双指针
     */
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }

        int l = 0, r = s.length - 1;

        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }


    /**
     * 另一种写法 用 ^ (bitwise XOR) 做swap
     *
     * 只适用于ASCII, 不适于unicode
     */
    public void reverseString_2(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }

        int l = 0, r = s.length - 1;

        while (l < r) {
            s[l] = (char) (s[l] ^ s[r]);
            s[r] = (char) (s[l] ^ s[r]);
            s[l] = (char) (s[l] ^ s[r]);
            l++;
            r--;
        }
    }
}
