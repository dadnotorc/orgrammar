/*
Easy
#Array
Microsoft
 */
package lintcode;

/**
 * 1823. Longest Prefix of Array
 *
 * Given two positive integers X and Y, and an array nums of positive integers.
 * We need to find the longest prefix index which contains an equal number of
 * X and Y，The number of X in the longest prefix must appear at least one time.
 * Return the maximum index of largest prefix if exist, otherwise return -1.
 *
 * Notice
 *  * The length of nums within range: [0, 1000000]
 *  * nums[i], X and Y within range: [1, 1 000000]
 *
 * Example 1:
 * Input: X = 2, Y = 4, nums: [1, 2, 3, 4, 4, 3]
 * Output: 3
 * Explanation: The longest prefix with same number of occurrences of 2 and 4 is
 * {1, 2, 3, 4}, so you should return 3
 *
 * Example 2:
 * Input: X = 7, Y = 42, nums = [7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7]
 * Output : 9
 * Explanation：The longest prefix with same number of occurrences of 7 and 42 is
 * {7, 42, 5, 6, 42, 8, 7, 5, 3, 6}, so you should return 9
 *
 * Example 3:
 * Input: X = 1, Y = 10, nums: [2, 3, 1]
 * Output: -1
 * Explanation: There are no prefix makes both 1 and 10 appear and  same number of occurrences
 */
public class _1823_LongestPrefixOfArray {

    /**
     * 1. 左指针按顺序读取数字, 右指针指向返回的index
     * 2. 遇到等量的X,Y后, 右指针随左指针移动, 寻找最长的prefix
     * 3. 遇到X,Y不等量时, 停止移动右指针
     */
    public int LongestPrefix(int X, int Y, int[] nums) {

        int l = 0, r = -1;
        int[] counts = new int[2];

        while (l < nums.length) {
            if (nums[l] == X) {
                counts[0]++;
            } else if (nums[l] == Y) {
                counts[1]++;
            }

            if (counts[0] != 0 && counts[0] == counts[1]) {
                r = l;
            }

            l++;
        }

        return r;
    }

    public int LongestPrefix_2(int X, int Y, int[] nums) {
        int l = 0, lastValidX = -1, foundX = 0, foundY = 0;

        while (l < nums.length) {
            if (nums[l] == X) {
                foundX++;
                if (foundX == foundY + 1)
                    lastValidX = l;
            } else if (nums[l] == Y) {
                foundY++;
            }

            l++;
        }

        if (foundX == 0) {
            return -1;
        } else if (foundX == foundY) {
            return nums.length -1;
        }

        return lastValidX - 1;
    }
}
