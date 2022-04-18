package lintcode;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 2389 · Removing duplicate numbers
 * Naive
 * #HashSet
 *
 * In class Solution, there is a method named deDuplicate, where the number and size of the elements
 * of an array of type Integer are not more than 10000, including 10000, and the array cannot be empty,
 * and the final returned Integer array must contain no duplicate elements and be sorted in ascending dictionary order.
 *
 * When you use collections, note that the generic type cannot be a basic data type
 *
 * Example
 *
 * Sample 1:
 * When the array passed in is 1 2 2 3 3 4 4, the output will be:
 * 1
 * 2
 * 3
 * 4
 *
 * Sample 2:
 * When the incoming array is 5 5 5 5 5 5, the output will be:
 *
 * 5
 */
public class _2389_Removing_duplicate_numbers {

    public Integer[] deDuplicate(Integer[] arr) {
        Set<Integer> set = new TreeSet<>();
        for (int i : arr) {
            set.add(i);
        }

        Integer[] ans = new Integer[set.size()];
        int index = 0;
        for (Integer integer : set) {
            ans[index] = integer;
            index++;
        }

        return ans;
    }
}
