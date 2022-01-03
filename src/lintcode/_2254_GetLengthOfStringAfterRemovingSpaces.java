/*
Naive
#String
 */
package lintcode;

/**
 * 2254 · Get the length of the string after removing spaces
 *
 * In this problem we have to write code to remove the spaces from the string str
 * and return the length of the string after removing the spaces.
 *
 * The Solution.java method defines a getLength method with a parameter str of type String
 * representing the string to be manipulated, you need to remove the spaces from the string
 * and get the length of the string after removing the spaces, and finally return the length
 * of the string with the value int type.
 *
 * Sample 1:
 * When the string is "he llo", your code should return 5.
 *
 * Sample Two:
 * When the array is "Good luck, boy.", your code should return 13
 */
public class _2254_GetLengthOfStringAfterRemovingSpaces {

    /**
     * 通过 char[], 读取空格字符 - 快
     */
    public int getLength(String str) {
        if (str == null || str.length() == 0) { return 0; }
        char[] chars = str.toCharArray();
        int ans = 0;
        for (char c : chars) {
            if (c != ' ') {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 使用 String API - replace() - 慢
     */
    public int getLength_2(String str) {
        if (str == null || str.length() == 0) { return 0; }
        str = str.replace(" ", "");
        return str.length();
    }
}
