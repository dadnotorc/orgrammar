/*
Easy
#Hash Table, #Math, #Array

 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1728 · X of a Kind in a Deck of Cards
 *
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that
 * it is possible to split the entire deck into 1 or more groups of cards, where:
 * - Each group has exactly X cards.
 * - All the cards in each group have the same integer.
 * - Returns true only if your optional x > = 2.
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 * Example1
 * Input: [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
 *
 * Example2:
 * Input: [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 *
 * Example3:
 * Input: [1]
 * Output: false
 * Explanation: No possible partition.
 */
public class _1728_XofaKindinaDeckofCards {

    /**
     * [1,1,2,2,2,2]
     * [1,1,1,1,2,2,2,2,2,2],
     * [1,1,1,2,2,2,3,3]
     *
     * 这题的考点在 hashmap 与 GCD
     */
    public boolean hasGroupsSizeX(List<Integer> deck) {
        if (deck.size() < 2) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            // 同样的功能, 更简化的写法
//            if (map.containsKey(num)) {
//                map.put(num, map.get(num) + 1);
//            } else {
//                map.put(num, 1);
//            }
        }

        int curGCD = -1;
        for (int value : map.values()) {
            if (value < 2) {
                return false;
            }
            if (curGCD == -1) {
                curGCD = value;
            } else {
                // 判断当前值的个数 与 上个值的个数, 是否存在最大公约数 Greatest Common Divisor GCD

                // 不能直接 return hasGCD(),  因为不等于的值还没遇到呢
                int newGCD = getGCD(curGCD, value);
                if (newGCD != -1) {
                    curGCD = Math.min(newGCD, curGCD);
                }
            }
        }

        return curGCD != -1 && curGCD != 1;
    }

    // 抄自 CSDN
    public int getGCD(int a, int b) {
        if (a < 0 || b < 0) {
            return -1; // 数学上不考虑为负数的约束
        }

        if (b == 0) {
            return a;
        }

        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return b;
    }

    @Test
    public void test1() {
        List<Integer> deck = new ArrayList<>();
        deck.add(1);
        deck.add(1);
        deck.add(1);
        deck.add(2);
        deck.add(2);
        deck.add(2);
        deck.add(3);
        deck.add(3);

        org.junit.Assert.assertTrue(hasGroupsSizeX(deck));
    }

}
