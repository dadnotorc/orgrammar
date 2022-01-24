package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 255 · Multi-string search
 * Easy
 * #String
 *
 * Given a source string sourceString and a target string array targetStrings,
 * determine whether each string in the target string array is a substring of the source string
 *
 * len(sourceString) <= 1000
 * sum(len(targetStrings[i])) <= 1000
 *
 * Example 1:
 * Input: sourceString = "abc" ，targetStrings = ["ab","cd"]
 * Output: [true, false]
 *
 * Example 2:
 * Input: sourceString = "lintcode" ，targetStrings = ["lint","code","codes"]
 * Output: [true,true,false]
 */
public class _0255_Multi_string_Search {

    public boolean[] whetherStringsAreSubstrings(String sourceString, String[] targetStrings) {
        if (targetStrings.length == 0) {
            return new boolean[0];
        }

        boolean[] ans = new boolean[targetStrings.length];
        Arrays.fill(ans, false); // 错误点 1

        // sourceString 中每个字符出现的位置 - <字符, 其出现位置的列表>
        // val 使用 list 是因为, 同个字符可能多次出现
        Map<Character, ArrayList<Integer>> map = new HashMap<>();

        char[] src = sourceString.toCharArray();

        for (int i = 0; i < src.length; i++) {
            if (!map.containsKey(src[i])) {
                map.put(src[i], new ArrayList<>());
            }
            map.get(src[i]).add(i);
        }

        for (int i = 0; i < targetStrings.length; i++) {
            char[] target = targetStrings[i].toCharArray();
            if (target.length == 0) { // target string = "" - 错误点 2
                ans[i] = true;
            } else if (map.containsKey(target[0])) {
                for (int j : map.get(target[0])) {
                    if (ans[i]) { // 错误点 3
                        break;
                    }
                    ans[i] = canBeFound(src, target, j);
                }
            } else {
                ans[i] = false;
            }
        }

        return ans;
    }

    public boolean contains(char[] src, char[] target, Map<Character, ArrayList<Integer>> map) {
        boolean isFound
    }


    public boolean canBeFound(char[] src, char[] target, int start) {
        int j = 0;

        for (int i = start; i < src.length && j < target.length; i++, j++) {
            if (src[i] != target[j]) {
                return false;
            }
        }

        return j == target.length;
    }

}
