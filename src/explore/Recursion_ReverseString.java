package explore;

import java.util.Arrays;

/**
 * 344. Reverse String
 * Easy
 *
 * Write a function that reverses a string.
 * The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array,
 *  you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 */
public class Recursion_ReverseString {

    /**
     * input is char array
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        helper(0, s.length - 1, s);
    }

    private static void helper(int l, int r, char[] s) {
        if (l >= r) {
            return;
        }
        helper(l+1, r-1, s); // 注意， 这里不能用 l++ r-- 会导致stack overflow
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }

    /**
     * input is String - String is immutable, so need to create a new String
     */
    public static String reverseString(String str) {
        if (str == null) { return "" ;}

        int strLen = str.length();
        if (strLen <= 1) { return str; }

        String left = str.substring(0, strLen / 2);
        String right = str.substring(strLen / 2, strLen);

        return reverseString(right) + reverseString(left);
    }



    public static void main(String[] args) {
        char[] in1 = {'h','e','l','l','o'};
        char[] exp1 = {'o','l','l','e','h'};
        reverseString(in1);
        System.out.println("Test 1 is " +
                (Arrays.equals(in1, exp1) ? "passed" : "failed"));

        char[] in2 = {'H','a','n','n','a','h'};
        char[] exp2 = {'h','a','n','n','a','H'};
        reverseString(in2);
        System.out.println("Test 2 is " +
                (Arrays.equals(in2, exp2) ? "passed" : "failed"));

        String in = "hello";
        String exp = "olleh";
        String act = reverseString(in);
        System.out.println("Test 3 is " +
                (exp.equals(act) ? "passed" : "failed"));

        in = "Hannah";
        exp = "hannaH";
        act = reverseString(in);
        System.out.println("Test 4 is " +
                (exp.equals(act) ? "passed" : "failed"));
    }
}
