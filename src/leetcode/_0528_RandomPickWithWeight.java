/*
Medium
#Binary Search, #Random
 */
package leetcode;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 *
 * Given an array w of positive integers, where w[i] describes the weight of index i,
 * write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 * 1. 1 <= w.length <= 10000
 * 2. 1 <= w[i] <= 10^5
 * 3. pickIndex will be called at most 10000 times.
 *
 * Example 1:
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 *
 * Example 2:
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 *
 * Explanation of Input Syntax:
 * The input is two lists: the subroutines called and their arguments.
 * Solution's constructor has one argument, the array w. pickIndex has no arguments.
 * Arguments are always wrapped with a list, even if there aren't any.
 *
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 *
 * 题目的意思是, w[i]的值为当前index的位权. 题目中 in proportion of weights的意思是:
 * 假设数组值总和为sum, w[i] / sum 为当前下标所占比重. 按此比重计算概率.
 * 例如[1,2,3,4], sum=1+2+3+4=10, w[0]/sum=1/10=10%, w[1]/sum=2/10=20%, w[2]/sum=30%, w[3]/sum=40%
 * 下标0出现几率为10%, 1出现几率为20%, 2为30%, 3为40%
 */
public class _0528_RandomPickWithWeight {

    /**
     * 解法1 - 直观的做法是先计算sum, 然后针对每个值做除法计算出现概率. 但是除法很可能产生double.
     *
     * 解法2 - 为了避免做除法, 可以采取计算前缀和
     * 例如[1,2,3,4]. 其前缀和为[1,3,6,10]. 从1到10之间取随机整数, 如果随机值为:
     * 1 (10%几率)        -> 返回下标0
     * [2,3]之间 (20%几率) -> 返回下标1
     * [4,6]之间 (30%几率) -> 返回下标2
     * [7,10]之间(40%几率) -> 返回下标3
     *
     * 原数组w不需要sorted, 因为计算prefixSum之后, 我们需要寻找的是随机整数在[1,sum]之间的范围, 以此寻找对应的下标
     *
     * 易错点:
     * 1. 二分法中, 挪动左右指针时, 是否需要+1
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
        int len = prefixSum.length;
        // 数组sum = prefixSum[len - 1] 前缀和最后一位
        // random取值范围 [0,sum) - 0 (inclusive) and sum(exclusive)
        // 我们需要的随机数范围 [1, sum], 所以 + 1
        int rnd = random.nextInt(prefixSum[len - 1]) + 1;

        // 二分法 原数组[1,2,3,4] 前缀和[1,3,6,10]
        // 假设随机选4, 4属于[4,6]之间, 所以因返回下标2
        int l = 0, r = len - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (prefixSum[m] == rnd) {
                return m;
            }

            if (prefixSum[m] < rnd) {
                // 随机值大于下标m对应的范围, 所以最后结果肯定大于m, 所以 +1
                l = m + 1;
            } else {
                // 虽然随机值小于prefixSum[m], 但是仍然有可能属于下标m对应的范围
                // 例如, 假设rnd=2, prefixSum[1]=3, 虽然rnd<prefixSum[]m, 但是2仍属于下标1对应范围[2,3]
                // 所以不能 +1
                r = m;
            }
        }

        return l; // 返回l或者r均可, 因为while结束时, l=r
    }
}
