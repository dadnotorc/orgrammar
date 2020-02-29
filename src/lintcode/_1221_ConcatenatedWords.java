/*
Hard
#DFS, #Trie, #DP
Amazon
 */
package lintcode;

import java.util.*;

/**
 * 1221. Concatenated Words
 *
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely
 * of at least two shorter words in the given array.
 *
 * Notice
 * - The number of elements of the given array will not exceed 10,000
 * -  The length sum of elements in the given array will not exceed 600,000.
 * -  All the input string will only include lower case letters.
 * -  The returned elements order does not matter.
 *
 * Example 1:
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Example 2:
 * Input: words = ["a","b","ab","abc"]
 * Output: ["ab"]
 * Explanation:
 * "ab" can be concatenated by "a" and "b".
 *
 * 注意:
 * Input: ["","cat","dog","catdog"]
 * Output: ["catdog"] - 不能包含"", 因为""不能有两个(不同的)单词组成
 */
public class _1221_ConcatenatedWords {

    /**
     * 相较前几种写法, 此次使用DFS取代boolean[], 速度更快 - 56ms
     */
    public List<String> findAllConcatenatedWordsInADict_4(String[] words) {
        List<String> ans = new ArrayList<>();

        HashSet<String> dict = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            dict.remove(word);

            // 利用字典中现有单词, 检查能否组成新单词
            // 记住 "" 不能加入
            if (canConstruct(dict, word, ""))
                ans.add(word);

            dict.add(word);
        }

        return ans;
    }

    /*
    定义: 使用dict中现有单词, 组成新单词, 并存入字典中. 并在字典中寻找是否存在目标单词
    出口: 当在字典中找到目标单词时, 返回true
    拆解: 读取当前单词的前缀, 如前缀存在于字典中, 将其传入下一层递归
          前缀从empty string "" 开始, 每次加上字典中存在的单词, 并将新组成的单词加入字典
     */
    private boolean canConstruct(HashSet<String> dict, String word, String prefix) {
        if (dict.contains(word))
            return true;

        for (int i = 1; i < word.length(); i++) {
            String sub = word.substring(0, i);

            // 目标单词的前缀存在于字典中时, 则将前缀从单词中取出, 将后半部传入下一层
            if (dict.contains(sub)) {
                String newPrefix = prefix + sub; // 利用现在单词, 能够组成新单词(newPrefix), 所以将其加入字典中
                dict.add(newPrefix); // 无需担心HashSet是否已存在newPrefix, HashSet特性保证不会重复
                if (canConstruct(dict, word.substring(i), newPrefix)) {
                    return true;
                }
            }
        }

        return false;
    }




    /**
     * 76ms 更快一些. 区别是: 在两层for loop的内层里 (for int j ...)
     * j 从 i-1 开始递减, 更好的利用已经查好的较长的单词, 而不是每次都从头开始查
     */
    public List<String> findAllConcatenatedWordsInADict_3(String[] words) {
        List<String> ans = new ArrayList<>();

        HashSet<String> dict = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            // 检查每一个word能否被dict中其他单词组成

            // dp[i]表示subtring(0,i)可否由其他单词组成
            // dp[0]指empty string -> 可以有其他单词组成 (不取任何单词)
            boolean[] dp = new boolean[word.length() + 1];
            Arrays.fill(dp, false); // 别忘了先全部赋值false
            dp[0] = true;

            dict.remove(word); // 避免加入字典中存在的单词

            for (int i = 1; i <= word.length(); i++) { // i为边界, exclusive
                for (int j = i- 1; j >= 0; j--) { // 反向查, 更好的利用已查好的较长单词
                    if (dp[j] && dict.contains(word.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            dict.add(word);
            dp[0] = false; // 避免加入"" (empty string)

            if (dp[word.length()]) {
                ans.add(word);
            }
        }

        return ans;
    }




    /**
     * 339ms 比前一种写法稍微快一点
     */
    public List<String> findAllConcatenatedWordsInADict_2(String[] words) {
        List<String> ans = new ArrayList<>();

        HashSet<String> dict = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            // 检查每一个word能否被dict中其他单词组成

            // dp[i]表示subtring(0,i)可否由其他单词组成
            // dp[0]指empty string -> 可以有其他单词组成 (不取任何单词)
            boolean[] dp = new boolean[word.length() + 1];
            Arrays.fill(dp, false); // 别忘了先全部赋值false
            dp[0] = true;

            dict.remove(word); // 避免加入字典中存在的单词

            for (int i = 1; i <= word.length(); i++) { // i为边界, exclusive
//                for (int j = 0; j < i; j++) {
//                    if (dp[j] && dict.contains(word.substring(j, i))) {
//                        dp[i] = true;
//                        break;
//                    }
//                }
                for (int j = i- 1; j >= 0; j--) {
                    if (dp[j] && dict.contains(word.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            dict.add(word);
            dp[0] = false; // 避免加入"" (empty string)

            if (dp[word.length()]) {
                ans.add(word);
            }
        }

        return ans;
    }



    /**
     * DP - 107. Word Break 进阶版 - 416ms
     * 1. 遍历数组, 创建字典
     * 2. 再次遍历数组, 检查当前单词是否可由字典中较短的两个单词组成
     *    a) substring不能等同当前单词, 不然就不能满足使用两个单词的条件
     *    b) "" 不可取, 理由通上
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();

        // 先将words从String[]转成HashSet, 方便查找
        HashSet<String> dict = new HashSet<>();
        for (String s : words)
            dict.add(s);

        for (String word : words) {
            // 检查每一个word能否被dict中其他单词组成

            // dp[i]表示subtring(0,i)可否由其他单词组成
            // dp[0]指empty string -> 可以有其他单词组成 (不取任何单词)
            boolean[] dp = new boolean[word.length() + 1];
            Arrays.fill(dp, false); // 别忘了先全部赋值false
            dp[0] = true;

            for (int i = 1; i <= word.length(); i++) { // i为边界, exclusive
                for (int j = 0; j < i; j++) {
                    String sub = word.substring(j, i);
                    // 注意: 与word break不同的地方是:
                    // 如果当前substring与word相同, 不能将其加入答案 - !word.equals(sub)
                    // 因为答案要求必须由两个不同的单词组成
                    if (dp[j] && dict.contains(sub) && !word.equals(sub)) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            if (dp[word.length()] && word.length() != 0) {
                ans.add(word);
            }
        }

        return ans;
    }
}
