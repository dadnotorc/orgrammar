/*
Medium
#String, #Recursion, #Backtracking
Amazon, Dropbox, Facebook, Google, Uber
FAQ+
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 425. Letter Combinations of a Phone Number
 *
 * Given a digit string excluded '0' and '1', return all possible letter
 * combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 *  1    2    3
 *      ABC	 DEF
 *  4    5    6
 * GHI  JKL	 MNO
 *  7    8    9
 * PQRS TUV	 WXYZ
 *
 * Notice
 * - Although the answer above is in lexicographical order, your answer could be in any order you want.
 *
 * Example 1:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * Explanation:
 * '2' could be 'a', 'b' or 'c'
 * '3' could be 'd', 'e' or 'f'
 *
 * Example 2:
 * Input: "5"
 * Output: ["j", "k", "l"]
 */
public class _0425_LetterCombinationsOfAPhoneNumber {

    /**
     * Iterative - 类似BFS, FIFO queue
     * 1. 每读取一个数字时, 将其对应的每一个字符, 分别加入队列中. 例如, input="23"
     *    开始读取前 -> 队列: [""]
     *    读取2 -> 队列: ["a"+"", "b"+"", "c"+""]
     *    读取3 -> 队列: ["a"+"d", "a"+"e", "a"+"f", "b"+"d", "b"+"e", "b"+"f", "c"+"d", "c"+"e", "c"+"f"]
     *
     * 易错点:
     * 1. 答案队列需要先加入""
     */
    public List<String> letterCombinations_iterative(String digits) {
        LinkedList<String> ans = new LinkedList<>(); // 这就是FIFO的queue

        if (digits == null || digits.length() == 0)
            return ans;

        ans.offer(""); // 这里很重要
        int curLen = 0; // 记录当前层 letter combination 的长度

        for (char c : digits.toCharArray()) {
            int curDigit = c - '0';
            String curLetters = MAPPING[curDigit];

            while (ans.peek().length() == curLen) { // FIFO, 所以peek出来的都是当前层的值
                String pre = ans.poll();

                for (char letter : curLetters.toCharArray()) {
                    ans.offer(pre + letter); // 组合新字母, 加入下一层
                }
            }

            curLen++; // 当前层全部检查完, 开始下一层
        }

        return ans;
    }


    /**
     * 另一种写法, 按照BFS常用写法
     *
     * 注意:
     * 这里不用 while(!queue.isEmpty()), 因为queue的最后一层正是我们需要的答案
     */
    public List<String> letterCombinations_iterative_2(String digits) {
        Queue<String> queue = new LinkedList<>(); // 这就是FIFO的queue

        if (digits == null || digits.length() == 0)
            return (List) queue;

        queue.offer(""); // 这里很重要

        for (char c : digits.toCharArray()) {
            int curDigit = c - '0';
            String curLetters = MAPPING[curDigit];

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String pre = queue.poll();

                for (char letter : curLetters.toCharArray()) {
                    queue.offer(pre + letter); // 组合新字母, 加入下一层
                }
            }
        }

        return (List) queue;
    }







    /**
     * Recursion - DFS的方式
     *
     * time:  O(k ^ n) - k=MAPPING中字符串长度的平均值(3~4), n=digits长度
     * space: O(1)
     */

    // 前两个为"", 因为对应下标0, 1
    private static final String[] MAPPING = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations_recursion(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return ans;

        combine(digits, ans, new StringBuilder(), 0);

        return ans;
    }

    /*
    定义: 将digits中每个数字对应的字符与其他数字对应的字符一一组合, 将组合好的字符串加入答案嘟列
    出口: 读完digits中每个数字后, 将当前字符串加入答案队列, 并返回
    拆解: 每次取出一个数字, 将其对应的每一个字符, 逐次加入sb中, 并带入下一层
     */
    private void combine(String digits, List<String> ans, StringBuilder sb, int index) {
        if (index == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        int curDigit = digits.charAt(index) - '0'; // 取出当前下标所指的数字

        for (char c : MAPPING[curDigit].toCharArray()) { // 针对当前数字对应的每个字符
            sb.append(c);

            combine(digits, ans, sb, index + 1);

            sb.deleteCharAt(sb.length() - 1); // 别忘了回溯时remove
        }
    }



    @Test
    public void test1() {
        List<String> expected = new LinkedList<>();
        expected.add("ad");
        expected.add("ae");
        expected.add("af");
        expected.add("bd");
        expected.add("be");
        expected.add("bf");
        expected.add("cd");
        expected.add("ce");
        expected.add("cf");

        org.junit.Assert.assertEquals(expected, letterCombinations_iterative("23"));
    }

}
