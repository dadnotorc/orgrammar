package interviews;

import org.junit.Test;

import java.util.Stack;

/**
 * Amazon OA - 2022 年 1月
 *
 * 可以套上 valid coupons 的场景, 考虑当前 coupon string 是否 valid
 *
 * Three rules for a valid string:
 * 1. An empty string is valid
 * 2. You can add same character to both side of a valid string X, and create another valid string yXy
 * 3. You can concatenate two valid strings X and Y, so XY will also be valid.
 *
 * Consists of only lowercase English characters.
 *
 * Ex: vv, xbbx, bbccdd, xyffyxdd are all valid.
 */
public class AMZN_2022_Valid_Coupons {

    /**
     * stack的解法适用于成对出现的字符, 例如aa, 或者 {}. 但是无法处理 aba 之类的, 那就得双指针了, 但是双指针无法处理 abbaxyyx 的情况
     *
     * it's essentially the valid parentheses question but with alphabets instead of parentheses
     * https://leetcode.com/problems/valid-parentheses/.
     * This can be solve in O(n) with a stack.
     */
    public boolean isValid(String s) {
       if (s == null || s.length() == 0) { // empty string is valid
           return true;
       }

       if ((s.length() & 1) != 0) { // 别忘了 & 操作要有自己的 () 包围着
           return false; // 奇数长度, 肯定无法成对出现
       }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test1() {
        String[] ss = {
                "vv",
                "xbbx",
                "bbccdd",
                "xyffyxdd",
        };

        for (String s : ss) {
            org.junit.Assert.assertTrue(isValid(s));
        }
    }
}
