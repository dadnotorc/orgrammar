/*
Medium
#DP, #String, #Trie
Amazon, Facebook, Google, Uber
FAQ++
 */
package lintcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 107. Word Break
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
public class _0107_WordBreak {

    /*
    非DP解法 - 查看s是否可以可以用字典中的字完整的组成
    先看s是否以某个字开头, 如果是去s后半部分的substring, 继续遍寻字典
     */


    /**
     * Trie
     *
     * 易错点:
     * 1. Trie+DP, 更新DP array时, 从后往前查后缀是否存在.
     *    另外, 遇到 cur char时, DP array应当查下 cur char的下一个index位
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

    /*
    Trie + DP
     */
    public boolean wordBreak_Trie_DP(String s, Set<String> dict) {
        if (s.equals(""))
            return true;

        TrieNode root = new TrieNode();
        buildTrie(dict, root);

        boolean[] canSegment = new boolean[s.length() + 1];
        canSegment[s.length()] = true; // 这里需要从后往前反着查后缀

        for (int i = s.length() - 1; i >= 0; i--) {
            TrieNode curNode = root;
            for (int j = i; curNode != null && j < s.length(); j++) {
                curNode = curNode.children[(int) s.charAt(j)];
                if (curNode != null && curNode.isEnd && canSegment[j + 1]) { // 注意这里看 canSegment[j + 1]
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[0];
    }


    /*
    Trie + Recursion
     */
    public boolean wordBreak_Trie_Recursion(String s, Set<String> dict) {
        if (s.equals(""))
            return true;

        TrieNode root = new TrieNode();
        buildTrie(dict, root);

        if (canConstruct(s, root, 0))
            return true;

        return false;
    }

    private boolean canConstruct(String s, TrieNode root, int startIndex) {
        TrieNode ptr = root;

        for (int curIndex = startIndex; curIndex < s.length(); curIndex++) {
            int i = (int) s.charAt(curIndex);
            if (ptr.children[i] == null)
                return false;

            ptr = ptr.children[i];

            if (ptr.isEnd) {
                if (curIndex == s.length() - 1) { // 已读完所有字符, 并在字典中找到相应的词/组合
                    return true;
                }

                if (canConstruct(s, root, curIndex + 1)) { // s前缀在字典中存在, 继续检查后缀是否存在
                    return true;
                }
            }
        }

        return false;
    }







    /**
     * DP - 在两层for loop的内层里 (for int j ...)
     *      j 从 i-1 开始递减, 更好的利用已经查好的较长的单词, 而不是每次都从头开始查
     */
    public boolean wordBreak_DP(String s, Set<String> dict) {

        // 字符串有length+1个分割点, 例如 _l_i_n_t_
        // canSegment[i]表示substring(0,i)在字典中存在, 所以能分割
        // 例如, "lintcode", ["lint", "code"]
        // canSegment = {T, F, F, F, T, F, F, F, T}
        boolean[] canSegment = new boolean[s.length() + 1];

        // 因为substring(0,0)是empty string, 可以被分割出来, 所以等于true
        canSegment[0] = true;

        for (int i = 1; i <= s.length(); i++) { // 查当前substring(0,i)是否可以被分割
            //for (int j = 0; j < i; j++) {
            for (int j = i - 1; j >= 0; j--) { // 反向查, 更好的利用已查好的较长单词
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
