/*
Hard
#Trie
Airbnb, Amazon, Google, Microsoft
 */
package lintcode;

import java.util.List;

/**
 * 132. Word Search II
 *
 * Given a matrix of lower alphabets and a dictionary. Find all words in the
 * dictionary that can be found in the matrix. A word can start from any position
 * in the matrix and go left/right/up/down to the adjacent position. One character
 * only be used once in one word. No same word in dictionary
 *
 * Example 1:
 * Input：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
 * Output：["again","can","dad","dog"]
 * Explanation：
 *   d o a f
 *   a g a i
 *   d c a n
 * search in Matrix，so return ["again","can","dad","dog"].
 *
 * Example 2:
 * Input：["a"]，["b"]
 * Output：[]
 * Explanation：
 *  a
 * search in Matrix，return [].
 *
 * Challenge
 * - Using trie to implement your algorithm.
 */
public class _0132_WordSearch2 {

    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
    }
}
