package leetcode;

import java.util.Random;

/**
 * 528. Random Pick with Weight - random city pick
 * Medium
 * #Binary Search, #Random
 * Google, Facebook
 *
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range
 * [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%),
 * and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * Example 1:
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0 (下标 0). The only option is to return 0 since there is only one element in w.
 *
 * Example 2:
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 *
 * Constraints:
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10^4 times.
 *
 *
 * 题目的意思是, w[i]的值为当前index的位权. 题目中 in proportion of weights的意思是:
 * 假设数组值总和为sum, w[i] / sum 为当前下标所占比重. 按此比重计算概率.
 * 例如[1,2,3,4], sum=1+2+3+4=10, w[0]/sum=1/10=10%, w[1]/sum=2/10=20%, w[2]/sum=30%, w[3]/sum=40%
 * 下标_0 出现几率为10%, 下标_1 出现几率为20%, 下标_2 为30%, 下标_3 为40%
 */
public class _0528_RandomPickWithWeight {

    /*
     解法1 - 直观的做法是先计算sum, 然后针对每个值做除法计算出现概率. 但是除法很可能产生double.

     解法2 - 为了避免做除法, 可以采取计算前缀和
     */


    /**
     * 解法2 - 前缀和 + 二分法
     * 1. 求前缀和
     * 2. 用二分法, 从 1 至 数组 sum 总和 之前取 随机整数, 看其分配在哪个区间
     *
     * 例如[1,2,3,4]. 其前缀和为[1,3,6,10]. 从1到10之间取随机整数, 如果随机值为:
     * 1 (10%几率)        -> 返回下标0
     * [2,3]之间 (20%几率) -> 返回下标1
     * [4,6]之间 (30%几率) -> 返回下标2
     * [7,10]之间(40%几率) -> 返回下标3
     *
     * 原数组w不需要sorted, 因为计算prefixSum之后, 我们需要寻找的是随机整数在[1,sum]之间的范围, 以此寻找对应的下标
     *
     * 易错点:
     * 1.  random.nextInt(sum) 时, random取值范围 [0,x) - 0 (inclusive) and sum(exclusive)
     *     因为这题我们需要从 [1, sum] 之间取值, 所以 .nextInt() + 1
     * 2. 二分法中, 挪动左右指针时, 是否需要+1
     */

    Random random;
    int[] prefixSum;

    public _0528_RandomPickWithWeight(int[] w) {
        this.random = new Random();
        this.prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int n = prefixSum.length;
        // 数组sum = prefixSum[len - 1] 前缀和最后一位
        // random取值范围 [0,sum) - 0 (inclusive) and sum(exclusive)
        // 我们需要的随机数范围 [1, sum], 所以 + 1
        int target = random.nextInt(prefixSum[n - 1]) + 1;

        // 二分法 原数组[1,2,3,4] 前缀和[1,3,6,10]
        // 假设随机选4, 4属于[4,6]之间, 所以因返回下标2
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (prefixSum[m] == target) {
                return m;
            }

            if (prefixSum[m] < target) {
                // target 值大于下标 m 对应的范围, 所以最后结果肯定大于 m, 所以 +1
                l = m + 1;
            } else {
                // 虽然 target 值小于prefixSum[m], 但是仍然有可能属于下标m对应的范围
                // 例如, 假设 target=2, prefixSum[1]=3, 虽然 target<prefixSum[1], 但是2仍属于下标1对应范围[2,3]
                // 所以不能 +1
                r = m;
            }
        }

        return l; // 返回l或者r均可, 因为while结束时, l=r
    }
}
