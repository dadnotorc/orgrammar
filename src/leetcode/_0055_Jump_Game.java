package leetcode;

/**
 * 55. Jump Game
 * Medium
 * #Array, #Greedy
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class _0055_Jump_Game {

    /*
    这题必须每格每格的去检查, 不然可能跳过, 错判 例如
    [1,3,8,0,0,0] - 如果到达 3 之后, 直接去检查 index 为 1 + 3 的格, 就会误判. 所以必须每格都要检查
     */

    /**
     * 从后往前遍历, 看从当前格能否去掉 last 格.
     * - 如果可以, 将当前格设为 last
     * - 如果不行, 继续往前遍历寻找有没有满足的格
     * 最后判断 last 格是不是 index 0 格
     *
     * 时间 O(n)
     * 空间 O(1)
     */
    public boolean canJump_3(int[] nums) {
        int last = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }

        return last == 0;
    }


    /**
     * 从前往后遍历, 判断从每格出发能到达的距离 VS 当前已经最远能到达的距离
     * 不断更新 最远能达的距离
     * 最后判断此距离是否 >= 最后一格
     *
     * 时间 O(n)
     * 空间 O(1)
     */
    public boolean canJump_4(int[] nums) {
        int cur = 0, max = 0; //　max 到当前能跳到的最远的index
        while (cur <= max) {
            max = Math.max(max, cur + nums[cur]);
            if (max >= nums.length - 1) {
                return true;
            }
            cur++;
        }
        return false;
    }


    /**
     * 从前往后一步一步检查, 只要没有遇到 0 都可以继续走
     * 当遇到 0 时, 而且已达到能走到最大 index 时, 就报 false
     *
     * 易错点: for 循环 走到 n - 1 就可以退出了, 不需要检查最后一位, 因为已经到了
     */
    public boolean canJump_5(int[] nums) {
        // if (nums == null || nums.length < 2) { return true; } // 不用特判, for 循环包含了

        int maxIndex = 0;
        for (int i = 0 ; i < nums.length - 1; i++) { // 遍历到 nums.length - 1 就可以结束了, 不用再检查最后一位了
            if (nums[i] == 0 && i == maxIndex) {
                return false;
            }
            maxIndex = Math.max(maxIndex, i + nums[i]);

            // 也可写成
//            maxIndex = Math.max(maxIndex, i + nums[i]);
//            if (i == maxIndex) { return false; }
//            if (maxIndex >= nums.length - 1) { return true; }
        }

        return true;
    }


    /**
     * 类似BFS
     */
    public boolean canJump_bfs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }

        int curPos = 0, curMax = 0;

        while (curPos <= curMax) {
            int rightMostFromCur = curMax;
            for (int i = curPos; i <= curMax; i++) {
                rightMostFromCur = Math.max(rightMostFromCur, i + nums[i]);
                if (rightMostFromCur >= nums.length - 1) {
                    return true;
                }
            }

            curPos = curMax + 1; // 注意! 这里+1进入下一层
            curMax = rightMostFromCur;
        }

        return false;
    }

    /**
     * 暴力解法 - 使用 size 为 n 的 boolean array
     * 经过每一格, 都判断能否到达当前格
     */
    public boolean canJump_bf(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }

        boolean[] canReach = new boolean[nums.length];
        canReach[0] = true;

        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (canReach[i] && i + nums[i] >= j) {
                    canReach[j] = true;
                    break;
                }
            }
        }

        return canReach[nums.length - 1];
    }
}
