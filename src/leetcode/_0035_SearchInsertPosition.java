/*
Easy
#Array, #Binary Search
 */
package leetcode;

/**
 * 35. Search Insert Position
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class _0035_SearchInsertPosition {

    /**
     * 易错点
     * 1. while循环 l <= r, 而不是<. 因为target在nums中未出现, 最后需要判断nums[l]与target
     * 2. 每次移动指针, l = m + 1 或者 r = m - 1. 必须+1或者-1, 否则会超时, TLE
     * 3. 最后返回时, 必须返回l, 而不是r
     */
    public int searchInsert_2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int l = 0, r = nums.length - 1;

        while (l <= r) { // 注意 这里用<= 因为当最后l=r时, 当前值可能仍不等于target, 要判断其与target大小
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        // 注意 最后只能返回l, 不能返回r
        // 当l=r=m时, 若nums[l] < target, 返回l下一位, 即m+1; 若nums[l] > target, 返回l
        // 如果返回r, 有可能返回的是m-1, 就错误了
        return l;
    }

    /**
     * 用二分法, 找到最后相邻的两个值, 将target放置在首/尾/中间位置
     * 写法太复杂
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return nums[0] < target ? 1 : 0;
            }
        }

        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }

        if (nums[l] == target) {
            return l;
        }
        if (nums[r] == target) {
            return r;
        }

        if (nums[l] > target) {
            return l;
        }
        if (nums[r] < target) {
            return r + 1;
        }

        return r;
    }
}
