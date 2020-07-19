/*
Easy
#String
 */
package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 937. Reorder Data in Log Files
 *
 * You have an array of logs. Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier. Then, either:
 * - Each word after the identifier will consist only of lowercase letters, or;
 * - Each word after the identifier will consist only of digits.
 *
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 * Constraints:
 * - 0 <= logs.length <= 100
 * - 3 <= logs[i].length <= 100
 * - logs[i] is guaranteed to have an identifier, and a word after the identifier.
 *
 * 此题类似 lintcode 1380. Log Sorting
 */
public class _0937_ReorderDataInLogFiles {

    /**
     * 新建Comparator, 在其内加入如下条件
     * 1. letter logs are sorted lexicographically. In case of ties, sort by identifier
     * 2. digit logs are in their original order, meaning they are not sorted
     * 3. letter logs should be in front of digit logs
     */
    public String[] reorderLogFiles(String[] logs) {

        class MyComparator implements Comparator<String> {
            @Override
            public int compare(String s1, String s2) {
                int index1 = s1.indexOf(' ');
                int index2 = s2.indexOf(' ');

                char logChar1 = s1.charAt(index1 + 1); // 记得+1, 看' '下一位
                char logChar2 = s2.charAt(index2 + 1);

                // 1. log1与log2都是digit logs, return 0 表示相等, 不sort
                // 2. log1或者log2其中有一个是digit log, 那此log靠后, 另一个为letter log靠前
                if (Character.isDigit(logChar1) || Character.isDigit(logChar2)) {
                    if (!Character.isDigit(logChar1)) { // log1为letter log 靠前
                        return -1;
                    } else if (!Character.isDigit(logChar2)) { // log2为letter log 靠前
                        return 1;
                    } else {
                        return 0;
                    }
                }

                // log1与log2都是letter logs, sort lexicographically
                // 先比较log部分, 如果两者相同, 则比较identifier
                // 如log部分不同, 则返回两者比较结果
                int logCompare = s1.substring(index1 + 1).compareTo(s2.substring(index2 + 1));
                if (logCompare == 0) {
                    return s1.substring(0, index1).compareTo(s2.substring(0, index2));
                }
                return logCompare;
            }
        }

        String[] res = new String[logs.length];
        System.arraycopy(logs, 0, res, 0, logs.length);
        Arrays.sort(res, new MyComparator());
        return res;
    }
}
