package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1046. Last Stone Weight
 * Easy
 *
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose the two heaviest rocks and smash them together.
 * Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 * - If x == y, both stones are totally destroyed;
 * - If x != y, the stone of weight x is totally destroyed,
 *    and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.
 * Return the weight of this stone (or 0 if there are no stones left.)
 *
 * Example 1:
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then
 *  that's the value of last stone.
 *
 * Note:
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class _1046_MaxHeap {
    public static int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0)
            return  0;

//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
//                (a,b) -> b - a);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                Comparator.reverseOrder());
        for (int i : stones) {
            maxHeap.offer(i);
        }

        while (maxHeap.size() >= 2) {
            int diff = maxHeap.poll() - maxHeap.poll();
            if (diff != 0)
                maxHeap.offer(diff);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        int act = lastStoneWeight(stones);
        System.out.println("Test 1 is " + (act == 1 ? "passed" : "failed"));
        if (act != 1)
            System.out.println("exp = 1; act = " + act);

        stones = null;
        act = lastStoneWeight(stones);
        System.out.println("Test 2 is " + (act == 0 ? "passed" : "failed"));
        if (act != 0)
            System.out.println("exp = 0; act = " + act);

        stones = new int[0];
        act = lastStoneWeight(stones);
        System.out.println("Test 3 is " + (act == 0 ? "passed" : "failed"));
        if (act != 0)
            System.out.println("exp = 0; act = " + act);
    }
}
