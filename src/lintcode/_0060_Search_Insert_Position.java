package lintcode;

/**
 * 60 · Search Insert Position
 * Easy
 * #Binary Search
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume NO duplicates in the array.
 *
 * Example 1:
 * Input: array = [1,3,5,6], target = 5
 * Output: 2
 * Explanation: 5 is indexed to 2 in the array.
 *
 * Example 2:
 * Input: array = [1,3,5,6], target = 2
 * Output: 1
 * Explanation: 2 should be inserted into the position with index 1.
 *
 * Example 3:
 * Input: array = [1,3,5,6], target = 7
 * Output: 4
 * Explanation: 7 should be inserted into the position with index 4.
 *
 * Example 4:
 * Input: array = [1,3,5,6], target = 0
 * Output: 0
 * Explanation: 0 should be inserted into the position with index 0.
 *
 * Challenge
 * O(log(n)) time
 */
public class _0060_Search_Insert_Position {

    /**
     * 二分
     */
    public int searchInsert(int[] a, int target) {
        if (a == null || a.length == 0) { return 0; }

        int l = 0, r = a.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return a[l] < target ? l + 1 : l;
    }

    /**
     * 另一种写法
     */
    public int searchInsert_2(int[] a, int target) {
        if (a == null || a.length == 0) { return 0; }

        int l = 0, r = a.length - 1;
        int ans = a.length; // 这里使用 ans 来记录最终结果的点

        while (l <= r) { // 这里与第一种写法不同
            int mid = l + (r - l) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1; // 这里与第一种写法不同
            }
        }

        return ans;
    }
}
