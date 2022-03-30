package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 * Medium
 * #Prime
 *
 * Given a sorted integer array nums, where the range of elements are
 * in the inclusive range [lower, upper], return its missing ranges.
 *
 * Example:
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 *
 * lintcode 641
 */
public class _0163_Missing_Ranges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();

        if (lower == Integer.MAX_VALUE) { return ans; }

        int left = lower;

        for (int num : nums) {
            if (num > left) { // find a missing range [left, num - 1]
                ans.add(getRangeStr(left, num - 1));

                if (num == Integer.MAX_VALUE) { // 避免下一步 int overflow
                    return ans;
                }

                left = num + 1;
            } else if (num == left) { // 数组中有 left, 所以移动左边界
                left++;
            } else {
                // num < left -> do nothing
            }
        }

        // 别忘了! 循环结束时, 可能仍未到 upper bound. 注意要 <=, 而只是 <
        if (left <= upper) {
            ans.add(getRangeStr(left, upper));
        }

        return ans;
    }

    private String getRangeStr(int a, int b) {
//        return a == b ? String.valueOf(a) : String.format("%d->%d", a, b);

        if (a > b) { return "invalid range"; }

        StringBuilder sb = new StringBuilder(String.valueOf(a));
        if (a < b) {
            sb.append("->").append(b);
        }
        return sb.toString();
    }
}
