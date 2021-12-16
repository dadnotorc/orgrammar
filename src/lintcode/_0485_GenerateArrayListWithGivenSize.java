/*
Naive
#Array

 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 485 · Generate ArrayList with Given Size
 *
 * Generate an arrayList with given size, initialize the array list with numbers from 1 to size.
 *
 * Example 1:
 * Input:  size = 4
 * Output: [1, 2, 3, 4]
 *
 * Example 2:
 * Input:  size = 1
 * Output: [1]
 */
public class _0485_GenerateArrayListWithGivenSize {

    public List<Integer> generate(int size) {
        List<Integer> ans = new ArrayList<>();
//        if (size < 1) {
//            return ans;
//        }

        for (int i = 0; i < size; i++) {
            ans.add(i + 1);
        }

        return ans;
    }
}
