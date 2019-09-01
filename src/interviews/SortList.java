package interviews;

import org.junit.Test;
import util.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class SortList {

    /**
     * Given a list of numbers with only 3 unique numbers (1, 2, 3),
     * sort the list in O(n) time.
     *
     * Example 1:
     * Input: [3, 3, 2, 1, 3, 2, 1]
     * Output: [1, 1, 2, 2, 3, 3, 3]
     *
     * Challenge: Try sorting the list using constant space.
     *
     * Google
     */
    public ArrayList<Integer> sortNums_hashmap(ArrayList<Integer> nums) {

        // 遍历数列,在hashmap中更新当前数字出现次数,然后创建新数列
        // todo 分析 ArrayList vs LinkedList

        if (nums == null)
            return null;

        HashMap<Integer, Integer> map = new HashMap<>();
        int size = nums.size();
        for (int i = 0; i <  size; i++) {
            int curNum = nums.get(i);

            if (map.containsKey(curNum))
                map.put(curNum, map.get(curNum) + 1);
            else
                map.put(curNum, 1);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        // 注意此循环中, i从1开始, 因为i为原数列中所含数字
        for (int i = 1; i <= map.size(); i++) {
            int curLen = map.get(i);
            for (int j = 0; j < curLen; j++) {
                ans.add(i);
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(3);
        nums.add(3);
        nums.add(2);
        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(1);
        System.out.println("Input: " + helper.listToString(nums));
        ArrayList<Integer> act = sortNums_hashmap(nums);
        System.out.println("Output: " + helper.listToString(act));
    }
}
