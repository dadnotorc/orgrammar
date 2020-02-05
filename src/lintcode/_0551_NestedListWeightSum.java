/*
Easy
DFS, Recursion
Facebook, LinkedIn
 */
package lintcode;

import util.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 551. Nested List Weight Sum
 *
 * Given a nested list of integers, return the sum of all integers in the
 * list weighted by their depth. Each element is either an integer, or a
 * list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Input: the list [[1,1],2,[1,1]],
 * Output: 10.
 * Explanation:
 * four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10
 *
 * Example 2:
 * Input: the list [1,[4,[6]]],
 * Output: 27.
 * Explanation:
 * one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3;
 * 1 + 4 * 2 + 6 * 3 = 27
 */
public class _0551_NestedListWeightSum {

    /* 解法1 - 递归 */
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    // 重点在于每次将 depth+1, 并带入下一层. 完成下层计算, 返回计算结果给上层, 并恢复depth
    private int helper(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.size() == 0)
            return 0;

        int sum = 0;

        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                sum += item.getInteger() * depth;
            } else {
                sum += helper(item.getList(),depth + 1);
            }
        }

        return sum;
    }

    // corner cases:
    // [], [[]], [[],[]]

    /* 解法2 - 循环 */
    public int depthSum_2(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0)
            return 0;

        int sum = 0;

        Queue<NestedInteger> queue = new LinkedList<>();
        // 将每层中的各个NestedInteger存入queue中
        for (NestedInteger item : nestedList)
            queue.offer(item);

        int depth = 1;
        while (!queue.isEmpty()) {
            // 记住每层开始时, 将长度保存, 因为在循环中, queue长度会变化
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                NestedInteger item = queue.poll();
                if (item.isInteger()) {
                    sum += item.getInteger() * depth;
                }
                else { // current item is a list
                    for (NestedInteger subItem : item.getList())
                        queue.offer(subItem);
                }
            }

            depth++;
        }

        return sum;
    }
}