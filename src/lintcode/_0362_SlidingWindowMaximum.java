/*
Hard
Deque, Two Pointers
Amazon, Facebook, Google, Microsoft
 */
package lintcode;

import org.junit.Test;

import java.util.*;

/**
 * 362. Sliding Window Maximum
 *
 * Given an array of n integer with duplicate number, and a moving
 * window(size k), move the window at each iteration from the start
 * of the array, find the maximum number inside the window at each moving.
 *
 * Example 1:
 * Input:  [1,2,7,7,8], 3
 * Output: [7,7,8]
 * Explanation：
 * At first the window is at the start of the array like this `[|1,2,7|,7,8]`, return the maximum `7`;
 * then the window move one step forward.`[1,|2,7,7|,8]`, return the maximum `7`;
 * then the window move one step forward again.`[1,2,|7,7,8|]`, return the maximum `8`;
 *
 * Example 2:
 * Input:  [1,2,3,1,2,3], 5
 * Output: [3,3]
 * Explanation:
 * At first, the state of the window is as follows: `[|1,2,3,1,2,|,3]`, a maximum of ` 3 `;
 * And then the window to the right one. `[1,|2,3,1,2,3|]`, a maximum of ` 3 `;
 *
 * Challenge
 * - o(n) time and O(k) memory
 */
public class _0362_SlidingWindowMaximum {

    /**
     * 解法1 - 使用单调(递减)双段队列 monotonic queue
     * 在每个循环结束时, 队列长度保持在 k-1
     *
     * monotonic queue属性是, 遇到新数字 n 时, 在queue中从后往前去掉所有 < n 的数字, 保证单调性
     * 如 n 小于queue中数字, 则将其排在队尾.
     *
     * 数组            队列   加入答案
     * [|1|,2,7,7,8]  [1]    n/a    i=0
     * [|1,2|,7,7,8]  [2]    n/a
     * [|1,2,7|,7,8]  [7]    7   从 i=k-1=2 开始,将队列首位加入答案, 并判断队列首位是否等于窗口中的首位, 如相同, 将其踢出队列
     * [1,|2,7,7|,8]  [7,7]  7
     * [1,2,|7,7,8|]  [8]    8
     *
     * time:  O(n)
     * space: O(k)
     */
    public List<Integer> maxSlidingWindow_Monotonic_Queue(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return ans;

        Deque<Integer> deque = new ArrayDeque<>();

        // 访问队列最前端 k-1 个数字, 放入队列, 此时队列最长长度 k - 1
        for (int i = 0; i < k - 1; i++)
           inQueue(deque, nums[i]);

        // 从第k个数字开始, 加数字入队列时, 将队列最前端加入答案中
        // 如队列最前端数字已到窗口最左边, 将其从队列中去除
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(deque, nums[i]);
            ans.add(deque.peekFirst());
            outQueue(deque, nums[i-k+1]); // i=k-1时, 看队列最前端是否是nums[0]
        }

        return ans;
    }

    // 遇到新数字时, 去掉队列中所有小于此数字的值
    private void inQueue(Deque<Integer> deque, int num) {
        // 从队列最后往前查, poll掉所有小于当前值的数字
        while (!deque.isEmpty() && (deque.peekLast() < num))
            deque.pollLast();

        deque.offerLast(num);
    }

    //
    private void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num)
            deque.pollFirst();
    }


    /**
     * 解法2 - 使用TreeSet
     * TreeSet保持长度为 k, 数值较大者靠前, 数值相同时下标较小者靠前
     * 读取新数字后, 加入set中. 再判断如set长度超过k, 则去掉已离开窗口的值.
     * 如set长度等于k时, 将首位加入答案
     *
     * time:  O(n*logk)
     * space: O(k+1)
     */
    public List<Integer> maxSlidingWindow_TreeSet(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return ans;

        Comparator<Node> myComparator = new Comparator<Node>() {
            @Override
            public int compare(Node newNode, Node oldNode) {
                if (newNode.val == oldNode.val)
                    return newNode.pos - oldNode.pos; // 数值相同时, 下标较小者在队列靠前

                return oldNode.val - newNode.val; // 数值较大者在队列靠前
            }
        };

        TreeSet<Node> set = new TreeSet<>(myComparator);

        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(i, nums[i]);
            set.add(node);

            if (set.size() > k)
                set.remove(new Node(i - k, nums[i - k])); //去掉已出窗口的值

            if (set.size() == k)
                ans.add(set.first().val); // 加最大值进答案
        }

        return ans;
    }

    class Node {
        int pos, val;
        public Node (int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }




    /**
     * 解法3 - 暴力 遇到大量数据时会超时
     * time:  O((n-k+1)*k)  当 k=n时, O(n)； 当 k= n/2 时, worst case, O(n^2)
     * space: O(1)
     */
    public List<Integer> maxSlidingWindow_bruteforce(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            ans.add(findMax(nums, i, i + k));

            if (i + k >= nums.length) // 右边已越界, window无法移动
                break;
        }

        return ans;
    }

    // [start, end] inclusive
    private int findMax(int[] nums, int left, int right) {
        int end = Math.min(nums.length, right);
        int max = nums[left];

        for (int i = left + 1; i < end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }




    @Test
    public void test1() {
        int[] nums = {1,2,7,7,8};
        List<Integer> exp = new ArrayList<>();
        exp.add(7);
        exp.add(7);
        exp.add(8);
        List<Integer> act = maxSlidingWindow_TreeSet(nums, 3);
    }
}
