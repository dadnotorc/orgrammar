/*
Easy
#Hash Table, #Bit Manipulation
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 136. Single Number
 *
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 * Input: [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class _0136_SingleNumber {

    /**
     * XOR - 因为x^x=0, 所以对于数组{x,x,y,y,z} 运算等于 x^x^y^y^z = (x^x)^(y^y)^z = 0^0^z = z
     * runtime ~ 0ms
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }

        return ans;
    }

    /**
     * runtime ~ 5ms
     */
    public int singleNumber_2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }

        return set.iterator().next();
    }

    /**
     * runtime > 600ms, bad
     */
    public int singleNumber_3(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : nums) {
            if (list.contains(i)) {
                list.remove(list.indexOf(i));
            } else {
                list.add(i);
            }
        }

        return list.get(0);
    }
}
