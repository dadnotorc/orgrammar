package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1124. Longest Well-Performing Interval
 * Medium
 *
 * We are given hours, a list of the number of hours worked per day for a
 *  given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours
 *  worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of
 *  tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 * Example 1:
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 * Constraints:
 * - 1 <= hours.length <= 10000
 * - 0 <= hours[i] <= 16
 */
public class _1124 {

    /**
     * LC上的参考解答
     *
     * We starts with a score = 0,
     * If the working hour > 8, we plus 1 point.
     * Otherwise we minus 1 point.
     * We want find the maximum interval that have strict positive score.
     *
     * After one day of work, if we find the total score > 0,
     * the whole interval has positive score,
     * so we set res = i + 1.
     *
     * If the score is a new lowest score, we record the day by seen[cur] = i.
     * And the maximum interval is i - seen[score - 1]
     *
     *
     * Complexity
     * - Time O(N)
     * - Space O(N)
     */
    public int longestWPI(int[] hours) {
        int res = 0, score = 0, n = hours.length;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            score += hours[i] > 8 ? 1 : -1;
            if (score > 0) {
                res = i + 1;
            } else {
                seen.putIfAbsent(score, i);
                if (seen.containsKey(score - 1))
                    res = Math.max(res, i - seen.get(score - 1));
            }
        }
        return res;
    }
}
