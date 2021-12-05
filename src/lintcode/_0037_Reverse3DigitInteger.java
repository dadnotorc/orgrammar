/*
Naive
#Math

 */
package lintcode;

/**
 * Reverse a 3-digit integer.
 *
 * You can assume the given number is larger or equal to 100 but smaller than 1000.
 *
 * Example 1:
 * Input:
 * number = 123
 * Output:
 * 321
 *
 * Example 2:
 * Input:
 * number = 900
 * Output:
 * 9
 */
public class _0037_Reverse3DigitInteger {

    public int reverseInteger(int number) {
        int ones = number % 10;
        int tens = (number / 10) % 10;
        int hundreds = (number / 100) % 10;

        return ones * 100 + tens * 10 + hundreds;
    }
}
