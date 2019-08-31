package interviews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TwoSum {
    /**
     * You are given a list of numbers, and a target number k.
     * Return whether there are two numbers in the list that add up to k.
     *
     * Example:
     * Given [4, 7, 1, -3, 2] and k = 5,
     * return true since 4 + 1 = 5.
     *
     * Challenge: Try to do it in a single pass of the list
     *
     * Facebook
     */
    public boolean two_sum(ArrayList<Integer> list, int k) {
        if (list == null || list.size() < 2)
            return false;

        // 此题用HashSet亦可
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (map.containsValue(list.get(i)))
                return true;
            else
                map.put(i, k - list.get(i));
        }

        return false;
    }

    @Test
    public void test1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(-3);
        list.add(2);
        org.junit.Assert.assertTrue(two_sum(list, 5));
    }

    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(-3);
        list.add(2);
        org.junit.Assert.assertFalse(two_sum(list, 10));
    }
}
