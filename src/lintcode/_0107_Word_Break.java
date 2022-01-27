package lintcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 107. Word Break
 * Medium
 * #DP, #String, #Trie
 * Amazon, Facebook, Google, Uber
 * FAQ++
 *
 * Given a string s and a dictionary of words dict, determine if s can be
 * broken into a space-separated sequence of one or more dictionary words.
 *
 * Example 1:
 * Input:  "lintcode", ["lint", "code"]
 * Output:  true
 *
 * Example 2:
 * Input: "a", ["a"]
 * Output:  true
 */
public class _0107_Word_Break {

    /*
    解法 1 - 暴力解法 - 查看s是否可以可以用字典中的字完整的组成
    先看s是否以某个字开头, 如果是去s后半部分的substring, 继续遍寻字典
    时间 - O(2 ^ (n-2))  ~  约为 O(2 ^ n)  -  exponential
    空间 - O(n)

    解法 2 - DP - 字符串有 n + 1 个分割点, 例如 _l_i_n_t_
    canSegment[i] 表示 substring(0,i) 在字典中存在, 所以能分割
    例如, "lintcode", ["lint", "code"]
    canSegment = {T, F, F, F, T, F, F, F, T}
    时间 - O(n ^ 3) - 两层循环 O(n), 因为会使用substring O(n)
    空间 - O(n)

    解法 3 - Trie + DP - canSegment[i] 表示 substring(i, n) 在字典中存在, 所以能分割
    更新 canSegment 时, 从后往前查后缀是否存在, 从短到长的检查
    遇到 cur char 时, canSegment 应当查 cur char 的下一个 index 位
    时间 O(w * l) + O(n ^ 2) - 前者是 build trie, 后者是遍历 s, 比对所有 substring;

     */


    /**
     * DP - DFS + memo
     */
    public boolean wordBreak(String s, Set<String> dict) {
        HashMap<String, Boolean> memo = new HashMap<>();
        return dfs(s, dict, 0, memo);
    }

    public boolean dfs(String s, Set<String> dict, int index, HashMap<String, Boolean> memo) {
        if (index == s.length()) {
            return true;
        }

        if (memo.containsKey(s.substring(index))) {
            return memo.get(s.substring(index));
        }

        for (int i = 1; index + i <= s.length(); i++) {
            if (dict.contains(s.substring(index, index + i)) &&
                    dfs(s, dict, index + i, memo)) {
                return true;
            }
        }

        memo.put(s.substring(index), false);

        return false;
    }



    /**
     * Trie
     */
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode() {
            children = new TrieNode[128]; // ASCII table has 128 characters
            isEnd = false;
        }
    }

    private void buildTrie(Set<String> dict, TrieNode root) {
        for (String word : dict) {
            TrieNode ptr = root;
            for (char c : word.toCharArray()) {
                int i = (int) c;
                if (ptr.children[i] == null) {
                    ptr.children[i] = new TrieNode();
                }
                ptr = ptr.children[i];
            }
            ptr.isEnd = true;
        }
    }

    /**
     * Trie + DP, canSegment[i] 表示 substring(i, n) 在字典中存在, 所以能分割
     *    - 更新 canSegment 时, 从后往前查后缀是否存在, 从短到长的检查
     *    - 遇到 cur char 时, canSegment 应当查 cur char 的下一个 index 位
     *
     * 时间 O(w * l) + O(n ^ 2) - 前者是 build trie, 后者是遍历 s, 比对所有 substring;
     * w targetStrings 中字符串个数, l 是这个字符串的平均长度
     *
     * TLE?
     */
    public boolean wordBreak_Trie_DP(String s, Set<String> dict) {
        if (s.equals(""))
            return true;

        TrieNode root = new TrieNode();
        buildTrie(dict, root);

        boolean[] canSegment = new boolean[s.length() + 1];
        canSegment[s.length()] = true; // 这里需要从后往前反着查后缀, 从短到长

        for (int i = s.length() - 1; i >= 0; i--) {
            TrieNode curNode = root;
            for (int j = i; curNode != null && j < s.length(); j++) {
                curNode = curNode.children[s.charAt(j)];
                if (curNode != null && curNode.isEnd && canSegment[j + 1]) { // 注意这里看 canSegment[j + 1]
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[0];
    }


    /**
     * Trie + Recursion
     */
    public boolean wordBreak_Trie_Recursion(String s, Set<String> dict) {
        if (s.equals(""))
            return true;

        TrieNode root = new TrieNode();
        buildTrie(dict, root);

        char[] chars = s.toCharArray();

        return canConstruct(chars, root, 0);
    }

    private boolean canConstruct(char[] chars, TrieNode root, int startIndex) {
        TrieNode ptr = root;

        for (int curIndex = startIndex; curIndex < chars.length; curIndex++) {
            if (ptr.children[chars[curIndex]] == null) {
                return false;
            }

            ptr = ptr.children[chars[curIndex]];

            if (ptr.isEnd) {
                if (curIndex == chars.length - 1) { // 已读完所有字符, 并在字典中找到相应的词/组合
                    return true;
                }

                if (canConstruct(chars, root, curIndex + 1)) { // s 前缀在字典中存在, 继续检查后缀是否存在
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * DP 优化
     * 1. 找出 dict 中 最长的字符串, 确定长度
     */
    public boolean wordBreak_DP_2(String s, Set<String> dict) {

        int n = s.length();

        int maxLen = 0;
        for (String word : dict) {
            maxLen = Math.max(maxLen, word.length());
        }

        boolean[] canSegment = new boolean[n + 1];

        // 因为substring(0,0)是empty string, 可以被分割出来, 所以等于true
        canSegment[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= maxLen; j++) {
                if (i < j) {
                    break;
                }
                if (!canSegment[i - j]) {
                    continue;
                }
                if (dict.contains(s.substring(i - j, i))) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[n];
    }





    /**
     * DP - 字符串有 length + 1 个分割点, 例如 _l_i_n_t_
     *      canSegment[i] 表示 substring(0,i) 在字典中存在, 所以能分割
     *      例如, "lintcode", ["lint", "code"]
     *      canSegment = {T, F, F, F, T, F, F, F, T}
     *
     * 时间 - O(n ^ 3) - 两层循环, substring 自己是 O(n)
     * 空间 - O(n)
     */
    public boolean wordBreak_DP(String s, Set<String> dict) {

        boolean[] canSegment = new boolean[s.length() + 1];

        // 因为substring(0,0)是empty string, 可以被分割出来, 所以等于true
        canSegment[0] = true;

        // 在两层 for loop 的内层里 (for int j ...)
        // j 从 i-1 开始递减, 更好的利用已经查好的较长的单词, 而不是每次都从头开始查
        for (int i = 1; i <= s.length(); i++) { // 查当前substring(0,i)是否可以被分割

            for (int j = i - 1; j >= 0; j--) { // 反向查, 而不是从 0 到 i - 1, 更好的利用已查好的较长单词

                // 如果substring(0,j)可以被分割, 且substring(j,i)在字典中存在 -> (0,i)也可以被分割
                if (canSegment[j] && dict.contains(s.substring(j, i))) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }






    /**
     * Recursion
     *
     * 易错点:
     * 1. corner case: Input: "", [""]; Output: True
     * 2. 递归时, 如果下一层返回值是false, 不能直接返回, 因为递归此时可能仍未完成
     *
     * 时间 - O(2 ^ (n-2))  ~  约为 O(2 ^ n)  -  exponential
     *
     * T(n) = T(n-1) + T(n-2) +.....T(1);
     * T(1) = T(2) = 1
     *
     * T(1) = T(2) = 1
     * T(3) = T(1) + T(2) = 1+1 =2; // 2^1
     * T(4) = T(1)+ T(2) + T(3) = 1+1+2 =4; //2^2
     * T(5) = T(1) + T(2) +T(3) +T(4) = 1+1+2+4 =8; //2^3
     *
     * 空间 - O(n)
     */
    public boolean wordBreak_recursion(String s, Set<String> dict) {
        if (s.equals("") || dict.contains(s))
            return true;

        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            String sub = s.substring(0, endIndex);
            if (dict.contains(sub)) {
                if (wordBreak_recursion(s.substring(endIndex), dict)) { // 不能直接return worldBreak() 因为递归可能仍在继续
                    return true;
                }
            }
        }

        return false;
    }




    @Test
    public void test1() {
        Set<String> dict = new HashSet<>();
        dict.add("lint");
        dict.add("code");

        org.junit.Assert.assertTrue(wordBreak_DP("lintcode", dict));
        org.junit.Assert.assertTrue(wordBreak_recursion("lintcode", dict));
        org.junit.Assert.assertTrue(wordBreak_Trie_DP("lintcode", dict));
        org.junit.Assert.assertTrue(wordBreak_Trie_Recursion("lintcode", dict));
    }

    @Test
    public void test2() {
        Set<String> dict = new HashSet<>();

        org.junit.Assert.assertTrue(wordBreak_DP("", dict));
        org.junit.Assert.assertTrue(wordBreak_recursion("", dict));
        org.junit.Assert.assertTrue(wordBreak_Trie_DP("", dict));
        org.junit.Assert.assertTrue(wordBreak_Trie_Recursion("", dict));
    }
}
