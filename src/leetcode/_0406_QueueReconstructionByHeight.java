/*
Medium
#Greedy
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. Queue Reconstruction by Height
 *
 * Suppose you have a random list of people standing in a queue. Each person is described
 * by a pair of integers (h, k), where h is the height of the person and k is the number
 * of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 * Example
 * Input:  [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class _0406_QueueReconstructionByHeight {

    /**
     * 先重新排序, 高个子(bigger h)在前; 个头相同时, smaller k者在前
     *
     * 易错点:
     * 1. 加入list时, index是当前数组的k值, 而不是h值
     * 2. 返回时, toArray需要new int[][]作为parameter
     * 3. new int[][]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    // same height h, smaller k goes in front
                    return a[1] - b[1];
                } else { // higher height h goes in front
                    return b[0] - a[0];
                }
            }
        });

        List<int[]> res = new ArrayList<>();
        for (int[] person : people) {
            res.add(person[1], person); // 注意, index = k, 而不是 h
        }

        // 两个都可以
        // return res.toArray(new int[people.length][2]);
        return res.toArray(new int[0][0]);
    }


    /**
     * 另一种写法
     */
    public int[][] reconstructQueue_2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[]b) {
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        for (int[] person : people) {
            res.add(person[0], person);
        }

        return res.toArray(new int[people.length][2]);
    }
}
