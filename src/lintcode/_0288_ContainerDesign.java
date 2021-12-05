/*
Easy


 */
package lintcode;

/**
 * Your task is to design a data structure to store a series of numbers
 * and include the following two functions:
 *
 * - add(element): add element into this data structure.
 * - getSum(): calculate the sum of numbers in this data structure.
 *
 * Example 1：
 * Input:
 * add(1)
 * add(2)
 * getSum()
 * add(4)
 * getSum()
 * Output:
 * [3, 7]
 * Explanation:
 * After add 1 and 2, the sum of numbers is 1 + 2 =  3.
 * After add 4, the sum of numbers is 1 + 2 + 4 = 7.
 *
 * Challenge
 * Can you calculate the sum with using O(1) time complexity?
 */
public class _0288_ContainerDesign {

    /**
     * @param element: Add element into this container.
     * @return: nothing
     */
    public void add(int element) {
        this.sum += element;
    }

    /**
     * @return: Sum of integers.
     */
    public int getSum() {
        return sum;
    }

    public int sum = 0;
}
