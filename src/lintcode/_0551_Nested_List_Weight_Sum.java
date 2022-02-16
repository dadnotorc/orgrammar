package lintcode;

import util.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 551. Nested List Weight Sum
 * Easy
 * #DFS, #Stack
 * Facebook, LinkedIn
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
 *
 * 等同 leetcode 339 (#prime)
 */
public class _0551_Nested_List_Weight_Sum {

    /**
     * 解法1 - 递归
     */
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
                sum += helper(item.getList(),depth + 1); // 注意是 item.getList(), 而不是 nestedList.getList()
            }
        }

        return sum;
    }

    // corner cases:
    // [], [[]], [[],[]]

    /**
     * 解法2 - 循环
     */
    public int depthSum_2(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) { return 0; }

        int sum = 0;

        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger item : nestedList) {
            queue.offer(item);
        }

        int depth = 1;
        while (!queue.isEmpty()) {
            // 记住每层开始时, 将长度保存, 因为在循环中, queue长度会变化
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                NestedInteger item = queue.poll();
                if (item != null) { // [], [[]], [[],[]]
                    if (item.isInteger()) {
                        sum += item.getInteger() * depth;
                    }
                    else {
                        for (NestedInteger subItem : item.getList()) {
                            queue.offer(subItem);
                        }
                    }
                }
            }

            depth++; // 别忘了递增 depth
        }

        return sum;
    }


    /**
     * stack 解法
     * 1. 将 list 中所有元素放入 stack, 给定 depth 为 1
     * 2. 逐个元素取出
     *    - 如果是 int -> 获得 int 值, 并计算
     *    - 如果仍是 list -> 获得 list, 将其中所有元素加入 stack 中, 且 depth + 1
     */
    class ResultType{
        NestedInteger nestedInteger;
        int depth;
        public ResultType(NestedInteger nestedInteger, int depth) {
            this.nestedInteger = nestedInteger;
            this.depth = depth;
        }
    }

    public int depthSum_3(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() < 1) { return 0; }

        int ans = 0;

        Stack<ResultType> stack = new Stack<>();

        for (NestedInteger nestedInteger : nestedList) {
            stack.push(new ResultType(nestedInteger, 1));
        }

        while (!stack.isEmpty()) {
            ResultType resultType = stack.pop();
            if (resultType.nestedInteger.isInteger()) {
                ans += resultType.nestedInteger.getInteger() * resultType.depth;
            } else {
                for (NestedInteger nestedInteger : resultType.nestedInteger.getList()) {
                    stack.push(new ResultType(nestedInteger, resultType.depth + 1));
                }
            }
        }

        return ans;
    }
}