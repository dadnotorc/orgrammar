package leetcode;

/**
 * 1094. Car Pooling
 * Medium
 *
 * You are driving a vehicle that has capacity empty seats initially available
 *  for passengers.  The vehicle only drives east
 *  (ie. it cannot turn around and drive west.)
 *
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location]
 *  contains information about the i-th trip:
 *  - the number of passengers that must be picked up,
 *  - and the locations to pick them up and drop them off.
 *  The locations are given as the number of kilometers due east from your
 *   vehicle's initial location.
 *
 * Return true if and only if it is possible to pick up and drop off all
 *  passengers for all the given trips.
 *
 * Constraints:
 * 1. trips.length <= 1000
 * 2. trips[i].length == 3
 * 3. 1 <= trips[i][0] <= 100
 * 4. 0 <= trips[i][1] < trips[i][2] <= 1000
 * 5. 1 <= capacity <= 100000
 *
 * 获得一个新trip的时候, 记录上站乘客人数和下站乘客人数, 最后遍历所有站, 当某站人数超过总容量时返回false
 *
 * 时间复杂度: O(1). 因为trips总数不超过1000, 站台总数1001. 两次循环不超过2001, 仍为常数
 * 空间复杂度: O(1). 需要长度为1001的数组
 */
public class _1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        // 0 <= trips[i][1] < trips[i][2] <= 1000 总有1001站, [0, 1000]
        int locCount = 1001;
        int[] locations = new int[locCount]; // 记录每站乘客人数

        for (int[] t: trips) {
            locations[t[1]] += t[0]; // 上站乘客
            locations[t[2]] -= t[0]; // 下站乘客
        }

        for (int i = 0, cur = 0; i < locCount; i++) {
            cur += locations[i];
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}
