/*
Hard
#Backtracking, #Trie
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 * Note:
 * 1. All inputs are consist of lowercase letters a-z.
 * 2. The values of words are distinct.
 *
 * 此题类似 lintcode 132. Word Search II
 */
public class _0212_WordSearch2 {

    /**
     * 使用 DFS + Trie
     *
     * 用TrieNode.word记录当前已找到的词
     *
     * 易错点:
     * 1. 因为字典中可能存在 dog dogs, 当已找到dog时, 不能停止搜索, 因为's'可能在周围, 这样就能找到两个词
     */
    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            this.children = new TrieNode[26]; // 26个小写字母
            this.word = null; // 当某个TrieNode.word不为空, 说明已找到一个完整word

            // 注意! 遇到TrieNode.word不为空时, 不应该停止搜索, 例如 dog dogs 两者都应当被找到
        }
    }

    public List<String> wordSearchII(char[][] board, String[] words) {

        List<String> ans = new ArrayList<>();

        if (board == null || board.length == 0 || board[0].length == 0 || words.length == 0)
            return ans;

        TrieNode root = new TrieNode();
        buildTrie(words, root);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, i, j, ans); // 从同一个点出发, 可能找到多组词, 例如 dog dogs, 应找到全部并加入ans
            }
        }

        return ans;
    }

    /*
    假设 input=["cat", "cats", "dog"]
         root
        /     \
       c      d
       |      |
       a      o
       |      |
       t-cat  g-dog
       |
       s-cats
     */
    private void buildTrie(String[] words, TrieNode root) {
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = word; // 别忘了在该点记录词语
        }
    }


    // 注意! 这个dfs不用boolean而是void, 因为可能有多个字符串 share the same path, eg dog dogs
    private void dfs(char[][] board, TrieNode node, int i, int j, List<String> ans) {
        char c = board[i][j];

        // 出口: 已访问过 或者 当前找到的词在字典中不存在 (即坐标(i,j)无效)
        if (c == '#' || node.children[c - 'a'] == null) // 在board上用'#'标记已访问过的点
            return;

        node = node.children[c - 'a'];

        if (node.word != null) { // 找到一个词
            ans.add(node.word);
            node.word = null; // 以免重复

            // 注意, 这里不return, 因为可能仍有类似的词, 例如 dog dogs 的后者还没找到
        }

        // 拆解: 访问相邻坐标, 带入递归, 寻找下一个有效字符
        board[i][j] = '#'; // 标记当前坐标已访问

        if (i > 0)
            dfs(board, node, i - 1, j, ans); // 向左

        if (j > 0)
            dfs(board, node, i, j - 1, ans); // 向上

        if (i < board.length - 1)
            dfs(board, node, i + 1, j, ans); // 向右

        if (j < board[0].length - 1)
            dfs(board, node, i, j + 1, ans); // 向下

        board[i][j] = c; // 别忘了复原
    }
}
