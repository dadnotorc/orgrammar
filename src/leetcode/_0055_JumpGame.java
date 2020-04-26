/*
Medium
#Array, #Greedy
 */
package leetcode;

/**
 * 55. Jump Game
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
public class _0055_JumpGame {

    /**
     * 别人写的
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
     * 我根据 canJump_bfs() 修改的
     */
    public boolean canJump_4(int[] nums) {
        int curPos = 0, rightMost = 0; //　rightMost 到当前能跳到的最远的index
        while (curPos <= rightMost) {
            rightMost = Math.max(rightMost, curPos + nums[curPos]);
            if (rightMost >= nums.length - 1) {
                return true;
            }
            curPos++;
        }
        return false;
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
     * 暴力解法
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
