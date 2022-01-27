package lintcode;

import java.util.*;

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

    /*
    解法 1 - 一个一个的去比较. 把 targetStrings 中所有字符串 放入 hashtable. 然后从 sourceString中
    创建出所有可能的 substring, 一一去 hashtable 中找寻.
    时间 - O(w) + O(n ^ 2); w 是 targetStrings 中字符串的个数; n 是 sourceString 的长度
    前者是创建 hashtable, 后者是对比 sourceString 的所有 substring

    解法 2 - 使用 Map 记录 sourceString 中每个字符出现的位置, 避免去检查那些不可能的位置
    时间 O(n) + O(w * l) - n 遍历 sourceString, 创建 map; w targetStrings 中字符串个数, l 是这个字符串的平均长度

    解法 3 - 使用 Trie nodes 记录 targetStrings 中的所有字符串
    时间 O(w * l) + O(n ^2) 前者是 build trie, 后者是遍历 sourceString, 找寻匹配 - 效率不如 解法 2
     */


    /**
     * 将 targetStrings 中 所有字符串, 用 trie 来记录.
     * 当字符串完结时, trie node 中用 targetIndex 函数记录 其在 targetStrings 中的下标
     *
     * 从后往前遍历 sourceString 中的每个字符 及其之后的子字符串, 判断是否在 trie 上出现
     *
     * 别忘了 targetStrings 中存在 "" 的特例, 别忘了!
     *
     *
     */
    class TrieNode{
        TrieNode[] children; // 注意这里是个 array
        int targetIndex;
        public TrieNode() {
            this.children = new TrieNode[256]; //ascii table 中 有 256 位字符
            targetIndex = -1; // 此值是 targetStrings 中的下标值, 如果为 -1, 表示不存在
        }
    }

    private void buildTrie(String[] targetStrings, TrieNode root) {
        for (int i = 0; i < targetStrings.length; i++) {
            TrieNode node = root;
            for (int j = 0; j < targetStrings[i].length(); j++) {
                int index = targetStrings[i].charAt(j);

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.targetIndex = i;
        }
    }

    public boolean[] whetherStringsAreSubstrings_trie(String sourceString, String[] targetStrings) {
        if (targetStrings == null || targetStrings.length == 0) {
            return new boolean[0];
        }

        TrieNode root = new TrieNode();
        buildTrie(targetStrings, root);

        boolean[] ans = new boolean[targetStrings.length];
        Arrays.fill(ans, false);

        for (int i = sourceString.length() - 1; i >= 0; i--) { // 这里要一定要从后往前遍历
            TrieNode node = root;
            for (int j = i; node != null && j < sourceString.length(); j++) {
                node = node.children[sourceString.charAt(j)];

                if (node != null && node.targetIndex > -1) { // 要放在 node = node.children 的后面, 不然会跳过最后一个字符的判断
                    ans[node.targetIndex] = true;

                    // 这里不能 break, 不然会跳过一些判断, 例如
                    // source = "codes", targets = ["code", "codes"]
                }
            }
        }

        // targetString 可能为 "" 的, 又忘了!!!
        if (root != null && root.targetIndex > -1) {
            ans[root.targetIndex] = true;
        }

        return ans;
    }




    /**
     * 1. 用 map 记录 sourceString 中 每个字符出现的位置, 注意 字符可能多次出现, 所以 value 部分用 list记录
     * 2. 对 targetStrings 中的每个字符串 判断是否存在于 sourceString中
     *    - 特判, 当前 targetString 为空, 则直接登记答案为 true
     *    - 通过 targetString 第一个字母 在 map 中的位置, 遍历查找看是否存在
     *
     * 时间 O(m + n) - m: sourceString 长度, n: targetStrings 中所有字符串的总长度
     * 空间 O(m)
     */
    public boolean[] whetherStringsAreSubstrings(String sourceString, String[] targetStrings) {
        if (targetStrings == null || targetStrings.length == 0) {
            return new boolean[0];
        }

        boolean[] ans = new boolean[targetStrings.length];
        Arrays.fill(ans, false); // 之前犯错点 1

        if (sourceString == null || sourceString.length() == 0) {
            return ans;
        }

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
            if (targetStrings[i].length() == 0) { // target string = "" - 错误点 2
                ans[i] = true;
            } else {
                ans[i] = contains(src, targetStrings[i].toCharArray(), map);
            }
        }

        return ans;
    }

    public boolean contains(char[] src, char[] target, Map<Character, ArrayList<Integer>> map) {
        if (map.containsKey(target[0])) {
            for (int i : map.get(target[0])) {
                if (isSubArray(src, target, i)) { // 错误点 3
                    return true;
                }
            }
        }

        return false;
    }


    public boolean isSubArray(char[] src, char[] target, int start) {
        int j = 0;

        for (int i = start; i < src.length && j < target.length; i++, j++) { // 注意边界判断, src 和 target都要考虑
            if (src[i] != target[j]) {
                return false;
            }
        }

        return j == target.length;
    }

}
