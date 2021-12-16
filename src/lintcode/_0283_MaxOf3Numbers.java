/*
Naive
#Enumerate

 */
package lintcode;

/**
 * 283 · Max of 3 Numbers
 *
 * Given 3 integers, return the max of them.
 *
 * Example 1:
 * 	Input:  num1 = 1, num2 = 9, num3 = 0
 * 	Output: 9
 *
 * Example 2:
 * 	Input:  num1 = 1, num2 = 2, num3 = 3
 * 	Output: 3
 */
public class _0283_MaxOf3Numbers {

    public int maxOfThreeNumbers(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }

    public int maxOfThreeNumbers_2(int num1, int num2, int num3) {
        if (num1 > num2) {
            return Math.max(num1, num3);
        }

        return Math.max(num2, num3);
    }
}
