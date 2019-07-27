package lintcode.twosum;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 56. Two Sum
 * Easy
 * Apple, LinkedIn, Airbnb, Facebook, Dropbox, Microsoft, Amazon, Uber
 *
 * Given an array of integers, find two numbers such that they add up to a
 *  specific target number.
 *
 * The function twoSum should return indices of the two numbers such that
 *  they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are zero-based.
 *
 * You may assume that each input would have exactly one solution
 *
 * Example1:
 * numbers=[2, 7, 11, 15], target=9
 * return [0, 1]
 *
 * Example2:
 * numbers=[15, 2, 7, 11], target=9
 * return [1, 2]
 *
 * Challenge
 * Either of the following solutions are acceptable:
 * - O(n) Space, O(nlogn) Time
 * - O(n) Space, O(n) Time
 */
public class _0056_TwoSum {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     *
     * 时间复杂度: O(n). HashMap的put/get一般情况下为O(1)
     * 空间复杂度: O(n). 用于存储HashMap
     */
    public int[] twoSumHashMap(int[] numbers, int target) {
        // write your code here

        // 使用HashMap记录当前值的下标(index)以及当前值与目标值的差值.
        // 以差值作为entry key, 下标作为entry value
        // 在数组的后续搜索中, 如果遇到已存在于HashMap的值, 即找到了相应的一组
        // 假设数组中仅可能存在一组答案, 所以无需考虑重复值的存在
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                return new int[] {map.get(numbers[i]), i};
            }
            map.put(target - numbers[i], i);
        }

        return new int[0];
    }


    /**
     * 使用 two pointers
     * TODO 这段有bug
     */

    class Pair {
        int index;
        int value;

        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public int[] twoSumTwoPointers(int[] numbers, int target) {
        //先用pair记录当前值和对应的下标, 因为之后会对数组重新排序
        Pair[] pairs = new Pair[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            pairs[i] = new Pair(numbers[i], i);
        }

        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.value > o2.value) {
                    return 1;
                } else if (o1.value < o2.value) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int l = 0, r = pairs.length - 1;

        while (l < r) {
            if (pairs[l].value + pairs[r].value == target) {
                int indexL = pairs[l].index;
                int indexR = pairs[r].index;
                int[] result = {Math.min(indexL, indexR), Math.max(indexL, indexR)};
                return result;
            } else if (pairs[l].value + pairs[r].value < target) {
                l++;
            } else {
                r--;
            }
        }

        return new int[]{};
    }

    @Test
    void test1() {
        int[] numbers = {2,7,11,15};
        int target = 9;
        int[] expected = {0,1};
        int[] actualHashMap = (new _0056_TwoSum().twoSumHashMap(numbers, target));
        int[] actualTwoPointers = (new _0056_TwoSum().twoSumTwoPointers(numbers, target));
        assertArrayEquals(expected, actualHashMap);
        assertArrayEquals(expected, actualTwoPointers);
    }

    @Test
    void test2() {
        int[] numbers = {15,2,7,11};
        int target = 9;
        int[] expected = {1,2};
        int[] actualHashMap = (new _0056_TwoSum().twoSumHashMap(numbers, target));
        int[] actualTwoPointers = (new _0056_TwoSum().twoSumTwoPointers(numbers, target));
        assertArrayEquals(expected, actualHashMap);
        assertArrayEquals(expected, actualTwoPointers);
    }
}
