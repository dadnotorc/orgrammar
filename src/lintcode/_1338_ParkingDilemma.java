/*
Easy
#Enumerate
 */
package lintcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 1338 · Parking Dilemma
 *
 * There are many cars parked in the parking lot. The parking is a straight very long line and a parking slot
 * for every single meter. There are cars parked currently and you want to cover them from the rain by building
 * a roof. The requirement is that at least k cars are covered by the roof. What's the minimum length of the roof
 * that would cover k cars? The function has the following parameters:
 * - cars: integer array of length denoting the parking slots where cars are parked
 * - k: integer denoting the number of cars that have to be covered by the roof
 * - 1 <= n <= 10^5
 * - 1 <= k <= n
 * - 0 <= cars[i] <= 10^14
 * - All slots token by cars are unique
 *
 * Example:
 * Input: cars: [2, 10, 8, 17], k: 3
 * output: 9
 * Explanation: you can build a roof of length 9 covering all parking slots from the 2nd one to the 10th one,
 * so covering 3 cars at slots 2, 10, 8, there are no shorter roof  that can cover 3 cars, so the answer is 9
 *
 * [2, 10, 8, 17] 表示在第 2, 10, 8 和 17 位 分别停着 4 辆车. 当屋顶长为 9 米的时候, 能覆盖 2 ~ 10 这就这个车位, 且这9个车位
 * 中有 k = 3 辆车.
 *
 * https://leetcode.com/discuss/interview-question/402014/IBM-or-OA-2019-or-Parking-Dilemma/756848
 */
public class _1338_ParkingDilemma {

    /**
     * 先排序, 确定从头开始按顺序看, 哪些车位有车停
     */
    public int ParkingDilemma(int[] cars, int k) {
        Arrays.sort(cars);
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= cars.length - k; i++) {
            ans = Math.min(ans, cars[i + k - 1] - cars[i] + 1);
        }

        return ans;
    }


    // 题意理解错了
/*    public int ParkingDilemma(int[] cars, int k) {
        if (cars == null || cars.length == 0) { return 0; }

        if (cars.length == 1) { return 1; }

        if (cars.length <= k) { return maxDistance(cars, 0, cars.length - 1); }

        int min = Integer.MAX_VALUE;
        for (int i = 0, j = i + k - 1; j < cars.length; i++, j++) {
            int curMax = maxDistance(cars, i, j);
            min = Math.min(min, curMax);
        }

        return min;
    }

    public int maxDistance(int[] cars, int left, int right) {
        int first = Integer.MAX_VALUE, last = 0;
        for (int i = left; i <= right; i++) {
            first = Math.min(cars[i], first);
            last = Math.max(cars[i], last);
        }
        return last - first + 1;
    }*/

    @Test
    public void test1() {
        int[] cars = {95, 64, 43, 16, 31, 58, 93, 92, 49, 25, 62, 5, 40};
        assertEquals(22, ParkingDilemma(cars, 5));
    }
}
