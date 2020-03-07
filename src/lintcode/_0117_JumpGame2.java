/*
Medium
#Array, #Greedy
Amazon
FAQ
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 117. Jump Game II
 *
 * Given an array of non-negative integers, you are initially positioned
 * at the first index of the array. Each element in the array represents
 * your maximum jump length at that position. Your goal is to reach the
 * last index in the minimum number of jumps.
 *
 * Example 1
 * Input : [2,3,1,1,4]
 * Output : 2
 * Explanation : The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *
 * 注意 array element 是 non-negative, 即 >= 0
 *
 */
public class _0117_JumpGame2 {


    /**
     * Greedy解法 - 用BFS来理解. 注意, 这里每一层要找的是下一层最远能够达到的坐标, 换句话说, 就是尽量把更多的点圈入下一层.
     * 这样做的目的是, 用最短的步数/层数将end index圈入
     *
     * 千万不要理解成下一步要走去最远的那个点 (因为实际上要走的点不一定是下一层的最远点)
     *
     * input=[5,9,3,2,1,0,2,3,3,1,0,0]
     * 第一层包含: a[0]=5 -> 这一层最大值为0+a[0]=5, 意思是下一层最远可达a[5]
     *
     * 第二层包含: a[1]=9, a[2]=3, a[3]=2, a[4]=1, a[5]=0 -> 这一层最大值为1+a[1]=10, 意思是下一层最远可达a[10] (注意看这里, 选中的是能够到的最远距离a[1], 而不是当前层的最远点a[5])
     *
     * 第三层包含: a[6]=2, a[7]=3, a[8]=3, a[9]=1, a[10]=0 -> 这一层最大值为8+a[8]=11, 意思是下一层最远可达a[11]
     * a[11]即为end index, 所以返回第三层
     *
     * time:  O(n)
     */
    public int jump_Greedy(int[] A) {
        if (A == null || A.length < 2)
            return 0;

        int level = 0, curPos = 0;
        int curMax = 0; // the end element (index) for the current (BFS) level

        while (curPos <= curMax) { // curPos ~ curMax 即为当前层
            level++;
            int rightMostFromCur = curMax; // 寻找从当前层内的点出发, 能跳到的最远的点
            for (int i = curPos; i <= curMax; i++) {
                rightMostFromCur = Math.max(rightMostFromCur, i + A[i]);
                if (rightMostFromCur >= A.length - 1) {
                    return level;
                }
            }
            curPos = curMax + 1; // 记得先更新curPos, 后更新curMax
            curMax = rightMostFromCur;
        }

        return -1;
    }


    /**
     * 使用DP - 从前往后遍历
     * DP array记录到达当前index所需的最少步数
     *
     * time:  1 + 2 + ... + n-1 = O(n^2)
     */
    public int jump_dp_1(int[] A) {
        if (A == null || A.length < 2)
            return 0;

        int[] minSteps = new int[A.length]; // 记录到达当前index需要的最小步数
        Arrays.fill(minSteps, Integer.MAX_VALUE);
        minSteps[0] = 0; // 从0到0 最少需要0步

        for (int j = 1; j < A.length; j++) {
            for (int i = 0; i < j; i++) { // 从0到j-1中, 找出能够达到j的最少步数
                if (minSteps[i] != Integer.MAX_VALUE && i + A[i] >= j) { // index i is reachable, 同时从i到j只需要1步
                    minSteps[j] = Math.min(minSteps[j], minSteps[i] + 1);
                }
            }
        }

        return minSteps[A.length - 1];
    }




    /**
     * 假设此题一定有解
     *
     * 暴力解法 - 从end index开始往前遍历, 每次寻找能到达当前index的最左边的点.
     * 一定是最小/最左边的吗? - 是, 假设n是end index, n-3, n-2, n-1都可以到达n.
     * 能够跳到n-2或者n-1的点, 必然能够跳到n-3.
     * 可是能跳到n-3的点, 不一定能跳到后两者, 如此跳跃步数将会增加.
     *
     * time:  O(n^2) worst case
     */
    public int jump_bruteforce(int[] A) {
        if (A == null || A.length < 2)
            return 0;

        int ans = 0, curPosition = A.length - 1;

        while (curPosition != 0) {
            for (int i = 0; i < curPosition; i++) { // 从0开始以便找到最左边的点
                if (curPosition - i <= A[i]) {
                    ans++;
                    curPosition = i;
                    break;
                }
            }
        }

        return ans;
    }




    /* 下面的解答都有问题, 反面教材 */

    public int jump_bug(int[] A) {

        int[] stepsToEndFromCurIndex = new int[A.length];
        stepsToEndFromCurIndex[A.length - 1] = 0;

        helper(A, stepsToEndFromCurIndex, A.length - 2);

        return stepsToEndFromCurIndex[0];
    }

    /**
     * stack overflow.........
     *
     * DP array stepsToEndFromCurIndex[] - s[]记录从当前index跳到end index最短需要多少步
     * same length as input array A, end index = A.length - 1;
     *
     * s[end] = 0 - 从end index走到end index最短需要0步
     * s[i] = end - i <= A[i] ? 1 : min(s[i+1] : s[i+A[i]]) + 1; (i < n)
     * 意思是: 从i到end index距离有end - i.
     *        - 如果n-i<=A[i], 说明从i走到end只需要跳一次
     *        - 否则, 说明无法从i一步跳到end.
     *          此时在s[i+1]到s[i+A[i]]之间挑选出最低值j, 则s[i]=s[j] + 1; 即先跳到j,再一步跳到end
     *          注意!!!: 如果遇到s[j] = -1, 说明不能跳到此点, 需要skip
     * 注意:
     * - A[i] 可能等于0. 若遇到此情况, 直接将s[i]负值为-1, 说明无法从i跳到n
     */
    private void helper(int[] A, int[] s, int curIndex) {
        if (curIndex < 0)
            return;

        if (A[curIndex] == 0) {
            s[curIndex] = -1;
        } else {
            if (A.length - 1 - curIndex <= A[curIndex]) {
                s[curIndex] = 1;
            } else {
                int min = -1;
                for (int i = A[curIndex]; i >= 1; i--) {
                    if (s[curIndex + i] >= 0) {
                        min = min >= 0 ? Math.min(min, s[curIndex + i]) : s[curIndex + i];
                    }
                }
                s[curIndex] = min >= 0 ? min + 1 : -1;
            }
        }

        helper(A, s, curIndex - 1);
    }

    /**
     * 会超时....
     *
     * 定义: 找出并返回从当前index移动到end index需要多少步.
     *       curSteps记录到当前index已经走过多少步, minSteps记录已成功的path中最短的步数
     * 出口: 当前index == end index, 返回curSteps和minsteps中的较小值.
     *       如果当前index > end index, 说明跳过头了, 此路径无效, 返回 -1
     * 拆解: 从当前index对应的步数中, 遍寻所有可能, 带入下一层
     */
    private int helper(int[] maxLen, int curIndex, int stepsTaken) {
        if (curIndex == maxLen.length - 1) {
            return stepsTaken;
        }

        if (curIndex > maxLen.length - 1) {
            return -1;
        }

        int minStepsToEnd = Integer.MAX_VALUE;

        for (int i = 1; i <= maxLen[curIndex]; i++) {
            int curAttemp = helper(maxLen, curIndex + i, stepsTaken + 1);
            minStepsToEnd = curAttemp < 0 ? minStepsToEnd : Math.min(minStepsToEnd, curAttemp);
        }

        return minStepsToEnd;
    }





    @Test
    public void test1() {
        int[] A = {2,3,1,1,4};
        org.junit.Assert.assertEquals(2, jump_Greedy(A));
    }
}
