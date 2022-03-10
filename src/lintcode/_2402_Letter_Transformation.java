package lintcode;

import java.util.Scanner;

/**
 * 2402 · Letter Transformation
 * Naive
 * 
 * Given a string s consisting of capital letters only, the ith letter in the alphabet becomes the
 * (26 - i + 1)th letter (for example, A becomes Z), transform All letters in the string are output
 * to the standard output stream (console) through the System.out.print statement.
 * 
 * 0 <= the length of s <=20
 * Only uppercase letters in the input
 * 
 * Example 1
 * input: s = "ZXXVKGVW"
 * output: ACCEPTED
 * 
 * Example 2
 * input: s = "ABC"
 * output: ZYX
 */
public class _2402_Letter_Transformation {

    /*
    两个地方要注意:
    1. int 转 char 时, 一定要用 (char)
    2. 赋值 char 时, 别忘了将算好的 val 加上 'A'
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int length = s.length();

        char[] chars = new char[length];

        for (int i = 0; i < length; i++) {
            int val = s.charAt(i) - 'A';
            chars[i] = (char) ('A' + 25 - val); // 别忘了 + 'A'
        }

        System.out.println(chars);
        sc.close();
    }

}
