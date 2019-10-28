/*
Medium

Salesforce
 */
package lintcode;

import org.junit.Test;

/**
 * 1453. Minimum Amount
 *
 * Give an array price is the price of all the things you want to buy.
 * The discount for buying the current item is the cheapest price of the item
 * you bought before. If the discount is greater than the price, it will be
 * free. If the discount is less than the price, you only need to pay the current
 * price minus the discount price. Return the minimum cost of buying these things.
 *
 * Example
 * Example:
 * Input:
 * price: [5, 2, 1, 7]
 * Output: 11
 * Explanation：The price is the default when buying the first item, the discount
 * for the second item is 5, 2 < 5 so it is free, the third item discount is 2,
 * 1 < 2 so it is also free, and the discount for the last item is 1 , so it takes
 * 7 - 1 = 6, the total cost is 6 + 5 = 11.
 */
public class _1453_MinimumAmount {

    /**
     * time:  O(n)
     * space: O(1)
     */
    public int minimumAmount(int[] price) {
        if (price == null || price.length == 0)
            return 0;

        int ans = price[0], discount = price[0];

        for (int i = 1; i < price.length; i++) {
            ans += price[i] <= discount ? 0 : (price[i] - discount);

            if (price[i] < discount)
                discount = price[i];
        }

        return ans;
    }

    @Test
    public void test1() {
        int[] price = {5, 2, 1, 7};
        // 5 + 0 + 0 + 6 = 11
        org.junit.Assert.assertEquals(minimumAmount(price), 11);
    }

    @Test
    public void test2() {
        int[] price = {5, 1, 2, 7};
        // 5 + 0 + 1 + 6 = 12
        org.junit.Assert.assertEquals(minimumAmount(price), 12);
    }

    @Test
    public void test3() {
        int[] price = {1, 2, 5, 7};
        // 1 + 1 + 4 + 6 = 12
        org.junit.Assert.assertEquals(minimumAmount(price), 12);
    }
}
