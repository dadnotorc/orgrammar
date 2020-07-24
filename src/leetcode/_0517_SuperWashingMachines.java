/*
Hard
#Math, #DP
 */
package leetcode;

/**
 * 517. Super Washing Machines
 *
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 *
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress
 * of each washing machine to one of its adjacent washing machines at the same time .
 *
 * Given an integer array representing the number of dresses in each washing machine from
 * left to right on the line, you should find the minimum number of moves to make all the
 * washing machines have the same number of dresses. If it is not possible to do it, return -1.
 *
 * Example1
 * Input: [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 *
 * Example2
 * Input: [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 *
 * Example3
 * Input: [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all the three washing machines have the same number of dresses.
 *
 * Note:
 * - The range of n is [1, 10000].
 * - The range of dresses number in a super washing machine is [0, 1e5].
 */
public class _0517_SuperWashingMachines {

    /**
     * 1. 遍历洗衣机, 统计衣服总数, 计算平均每台洗衣机应当有几件衣服
     * 2. 对每台洗衣机
     *    a. 获得gain/loss值 (当前load - 平均值) 即当前洗衣机需要gain/loss的数量
     *    b. 计算每台机器需要移出的数量 = 当前量 + 前一台机器传入的量 - 最后应保留的量
     *    c. 比较转移量的绝对值 与 当前gain/loss值, 取较大者
     *       (前者表示当前需要移动的最大值, 后者如果为正,表示该机器需要移出的值)
     *
     * 例如 input = [1,0,5] 总衣量为6, 平均值为2, 所以需要得到[2,2,2]
     * 所以 loss array 或者 amountToPass array = [-1,-2,3], 意思是第一位要转出-1个(即得到1个), 第二位要转出-2个, 第三位要转出3个
     * 从左向右传递: [-1,-2,3] -> [0,-3,3] 意思是第二位最多需要转出-3个(即得到3个)
     * 再次传递:    [0,-3,3] -> [0,0,0] 第三位转出3个给第二位, 达到目的
     *
     * Input = [0,3,0], amountToPass array = [-1,2,-1]
     * [-1,2,-1] -> [0, 1, -1] -> [0,0,0]
     */
    public int findMinMoves(int[] machines) {
        int res = 0;
        int total = 0, n = machines.length;
        for (int load : machines) {
            total += load; // 统计总衣量
        }

        if (total % n != 0) { return -1; } // 无法均分, 返回-1

        int average = total / n;
        int amountToPass = 0;
        for (int load : machines) {
            // 每台机器需要移出的数量 = 当前量 + 前一台机器传入的量 - 最后应保留的量
            amountToPass = load + amountToPass - average;
            // 答案是 最大的转移量的绝对值 与 最大的gain/loss值 之间的较大者
            res = Math.max(res, Math.max(Math.abs(amountToPass), load - average));
        }

        return res;
    }


    /**
     * 算DP吗?
     */
    public int findMinMoves_2(int[] machines) {
        int res = 0;
        int total = 0, n = machines.length;
        for (int load : machines) {
            total += load; // 统计总衣量
        }

        if (total % n != 0) { return -1; } // 无法均分, 返回-1

        int average = total / n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + machines[i] - average;
            res = Math.max(res, Math.max(Math.abs(dp[i + 1]), machines[i] - average));
        }

        return res;
    }
}
