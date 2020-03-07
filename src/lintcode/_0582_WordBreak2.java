/*
Hard
#DP, #Backtracking, #DFS
Amazon, Dropbox, google, Twitter, Uber
 */
package lintcode;

import org.junit.Test;

import java.util.*;

/**
 * 582. Word Break II
 *
 * Given a string s and a dictionary of words dict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * Example 1:
 * Input："lintcode"，["de","ding","co","code","lint"]
 * Output：["lint code", "lint co de"]
 * Explanation：
 * insert a space is "lint code"，insert two spaces is "lint co de".
 *
 * Example 2:
 * Input："a"，[]
 * Output：[]
 * Explanation：dict is null.
 */
public class _0582_WordBreak2 {

    /*
    corner case: input = "", [""]; output = []
     */

    /**
     * 使用记忆化搜索, 利用HashMap存储找到的短句
     *
     */
    public List<String> wordBreak_MemorizedDFS(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return new ArrayList<>();

        Map<String, ArrayList<String>> map = new HashMap<>();
        return helper(s, wordDict, map);
    }

    private ArrayList<String> helper (String s, Set<String> dict, Map<String, ArrayList<String>> map) {
        // 出口
        if (map.containsKey(s)) {
            return map.get(s);
        }

        ArrayList<String> results = new ArrayList<>();

        if (s.length() == 0) { // s == ""
            return results;
        }

        // 拆解
        if (dict.contains(s)) {
            results.add(s);
        }

        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);

            // 如果prefix能被拆出来, 将suffix带入下一层, 用于判断是否也能被拆分
            if (dict.contains(prefix)) {
                String suffix = s.substring(i);
                ArrayList<String> segments = helper(suffix, dict, map);

                for (String segment : segments) {
                    results.add(prefix + " " + segment);
                }
            }
        }

        map.put(s, results);
        return results;
    }





    /**
     * DFS - without DP, memory limit exceeded
     */
    public List<String> wordBreak_DFS(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return new ArrayList<>();

        List<String> ans = new ArrayList<>();
        dfs_1(s, wordDict, ans, new ArrayList<>(), 0, 1);

        return ans;
    }

    private void dfs_1(String s, Set<String> dict, List<String> ans, List<String> curSentence, int startIndex, int endIndex) {
        if (startIndex == s.length()) {
            ans.add(printList(curSentence));
            return;
        }

        while (endIndex <= s.length()) {
            String sub = s.substring(startIndex, endIndex);
            if (dict.contains(sub)) {
                curSentence.add(sub);
                dfs_1(s, dict, ans, curSentence, endIndex, endIndex + 1);
                curSentence.remove(curSentence.size() - 1);
            }

            endIndex++;
        }
    }

    private String printList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }






    @Test
    public void test1() {
        Set<String> dict = new HashSet<>();
        dict.add("de");
        dict.add("ding");
        dict.add("co");
        dict.add("code");
        dict.add("lint");

        List<String> output = wordBreak_DFS("lintcode", dict);
    }

}
