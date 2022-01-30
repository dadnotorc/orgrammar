package leetcode;

import java.util.*;

/**
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 * Medium
 * #Array, #Greedy, #Sorting
 * Amazon
 *
 * You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 *
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 *
 * Example 1:
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 *
 * Example 2:
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class _1509_Min_Diff_Between_Largest_Smallest_Value_Three_Moves {

    /**
     * 上岸解法 - todo 再看看
     * 1. 先排序，首先方便计算最大值和最小值的差；
     * 2. 其次因为差值就是最大值最小值产生的，因此只需要去修改当前操作之后的数组最大值最小值即可
     *
     * DFS枚举每一种修改的方案，计算比较得出最小的差值即可
     */
    public int minDifference_4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        if(right <= 3) {
            return 0;
        }
        // 排序后,方便直接计算差值
        Arrays.sort(nums);
        return helper(nums, left, right, 3);
    }
    // brute force 解法
    public int helper(int[] arr, int left, int right, int steps){
        if(steps == 0) {
            return arr[right] - arr[left];
        }
        steps--;
        
        // 修改最小的的element
        int leftDiff = helper(arr, left + 1, right, steps);
        // 修改最大的element
        int rightDiff = helper(arr, left, right - 1, steps);
        // 取更小的
        return Math.min(leftDiff, rightDiff);
    }





    /**
     * 上一种做法中, 我们考虑了 单边最多移除 3 位的几种可能.
     * 可以把问题转化成, 使用一个 min heap 和一个 max heap, 每个保留4位 (单边3位 + 1)
     *
     * 但是这种做法, 对于 n 远大于 k 的情况, 时间不理想. k 为最多能改变的数字个数
     *
     * min heap
     * 1. In a Min-Heap the key present at the root node must be <= to among the keys present at all of its children.
     * 2. In a Min-Heap the minimum key element present at the root.
     * 3. A Min-Heap uses the ascending priority.
     * 4. In the construction of a Min-Heap, the smallest element has priority.
     * 5. In a Min-Heap, the smallest element is the first to be popped from the heap.
     *
     * max heap
     * 1. In a Max-Heap the key present at the root node must be >= among the keys present at all of its children.
     * 2. In a Max-Heap the maximum key element present at the root.
     * 3. A Max-Heap uses the descending priority.
     * 4. In the construction of a Max-Heap, the largest element has priority.
     * 5. In a Max-Heap, the largest element is the first to be popped from the heap.
     *
     * 简单地讲
     * min heap - 上升排序, 最小值先被 poll, 最后保留最大值
     * max heap - 下降排序, 最大值先被 poll, 最后保留最小值
     */
    public int minDifference_3(int[] nums) {
        if (nums == null || nums.length < 5) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });

        for (int i : nums) {
            minHeap.offer(i);
            maxHeap.offer(i);

            if (minHeap.size() > 4) {
                minHeap.poll();
            }
            if (maxHeap.size() > 4) {
                maxHeap.poll();
            }
        }

        int[] min = new int[4];
        int[] max = new int[4];
        for (int i = 0, j = 3; i < 4; i++, j--) {
            max[i] = minHeap.poll(); // min heap 保留最大值
            min[j] = maxHeap.poll(); // max heap 保留最小值
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, max[i] - min[i]);
        }

        return ans;
    }




    /**
     * 也是通过 窗口 来考虑, 但是无需遍历整个数组, 而只需要考虑如下几种窗口
     * 1. 仍是先排序
     * 2. 考虑如下几种窗口
     *    - 去掉最大的 3 个数
     *    - 去掉最大的 2 个数 + 最小的 1个数
     *    - 去掉最大的 1 个数 + 最小的 2 个数
     *    - 去掉最小的 3 个数
     * 也就是说 3 次 move, 分别考虑去掉这 4 种情况里的 3 个值. 返回最小的答案
     *
     * 时间 - O(nlogn)  排序
     * 空间 - O(1)
     */
    public int minDifference_2(int[] nums) {
        if (nums == null || nums.length < 5) {
            return 0;
        }

        Arrays.sort(nums);

        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, nums[n - (4 - i)] - nums[i]);
        }

        /* for 循环取代下面的 4 句
        ans = Math.min(ans, nums[n - 4] - nums[0]);
        ans = Math.min(ans, nums[n - 3] - nums[1]);
        ans = Math.min(ans, nums[n - 2] - nums[2]);
        ans = Math.min(ans, nums[n - 1] - nums[3]);
        */

        return ans;
    }


    /**
     * 1. 排序
     * 2. 移动一个长度为 n - 4 的窗口, 记录窗口内 4 个数字 两两之间的差值之和 (共 3 个差值). 例如
     *       0  1  1  4  6  6  6
     * 差值    1  0  3  2  0  0
     * 窗口1  [       ]            窗口之和 = 1 + 0 + 3 = 4
     * 窗口2     [       ]         窗口之和 = 0 + 3 + 2 =5
     * 窗口3        [      ]       窗口之和 = 3 + 2 + 0 = 5
     * 窗口4           [       ]   窗口之和 = 2 + 0 + 0 = 2
     * 窗口中最小的和为 2, 答案就是 2
     *
     * 时间 - O(nlogn) + O(n): 排序 + 遍历
     * 空间 - O(1)
     */
    public int minDifference(int[] nums) {
        if (nums == null || nums.length < 5) {
            return 0;
        }

        Arrays.sort(nums);

        int n = nums.length;
        int window = n - 4;
        int ans = Integer.MAX_VALUE;
        int sum = 0;

        int i = 1, j = 1;
        while (j < n) {
            sum += nums[j] - nums[j - 1];
            j++; // 之前犯错_1 - 不能写在 if 之后, 因为要及时判断 j 与 i 是否已达边界, 如果是, 则需要判断 ans
                 // 如果 j++ 在 if 之后, window 长度 = n - 1 - 4

            if (j - i == window) {
                ans = Math.min(ans, sum); // 之前犯错_2 - j 与 i 到达边界时, 要先判断 ans, 再做减法.
                sum -= nums[i] - nums[i - 1];
                i++;
            }
        }


        return ans;
    }
}
