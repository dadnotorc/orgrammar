/*
Medium
#Array, #Sorting, #Heap, #Prefix SUm
Microsoft
 */
package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 */
public class _1094_1001Stations {

    /**
     * 获得一个新trip的时候, 记录上站乘客人数和下站乘客人数, 最后遍历所有站, 当某站人数超过总容量时返回false
     *
     * 时间复杂度: O(1). 因为trips总数不超过1000, 站台总数1001. 两次循环不超过2001, 仍为常数
     * 空间复杂度: O(1). 需要长度为1001的数组
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 记录每一站上车下车的人数变化, 但是不包含当前车上的乘客总数,
        // 长度 为 1001, 因为 0 <= start < end <= 1000
        int[] changes = new int[1001]; // 记录每站乘客人数

        for (int[] t: trips) {
            // 这里不做当前乘客总数判断, 留在最后做. 因为 trips 没有遍历完, 不知道到站前车上乘客数量
            changes[t[1]] += t[0]; // 上站乘客
            changes[t[2]] -= t[0]; // 下站乘客
        }

        for (int i = 0, cur = 0; i < changes.length; i++) {
            cur += changes[i];
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果题目没有确定有多少站, 即无法得知是 1001 站, 无法使用 array, 则用 map 替代
     *
     * 使用 TreeMap 而不是常用的 HashMap, 理由是,
     * - HashMap does not preserve the iteration order,
     * - TreeMap preserve the order by using the compareTo() method or a comparator set in the TreeMap's constructor.
     *
     * 时间 O(n * logn)
     * 空间 O(n)
     */
    public boolean carPooling_2(int[][] trips, int capacity) {
        // 这里使用 TreeMap 的原因是, 它默认情况下就是按照key的大小来进行排序的（升序）
        Map<Integer, Integer> map = new TreeMap<>();

        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        int curPassengers = 0;
        // 直接取得 value set, 无需通过 key set 再得到 value
        for (int value : map.values()) {
            curPassengers += value;

            if (curPassengers > capacity) {
                return false;
            }
        }

        return true;
    }


    /**
     * sort + priority queue
     * 排序时, 将 fromi 较小者放在前
     * PQ中, 讲 toi 较小者放在前
     *
     * 易错点
     * 计算下车人数的时, 要用 while , 而不是 if, 要讲所有下车的人都去掉
     *
     * 时间 O(n * logn)
     * 空间 O(n)
     */
    public boolean carPooling_3(int[][] trips, int capacity) {
        Arrays.sort(trips, new Comparator<int[]>(){
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1]; // fromi 较小者靠前
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[2] - t2[2]; // toi 较小者靠前
            }
        });

        int curPassenger = 0;
        for (int[] trip : trips) {
            // 如果 PQ 中最前一位的 toi <= 当前 trip 的 fromi, 说明有人提前下车了, 应减少, 并将 pq 中最前一位 poll出来

            // 另外, 注意 这里用 while, 而不是 if, 要去掉所有下车的人
            while (!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                curPassenger -= pq.poll()[0];
            }

            // 不过 PQ 中什么情况, 上车的人都是要上的
            curPassenger += trip[0];
            if (curPassenger > capacity) {
                return false;
            }

            // 别忘了把 trip 放进 PQ
            pq.offer(trip);
        }

        return true;
    }

    @Test
    public void test1() {
        int[][] trips = new int[][] {{2,1,5},{3,3,7}};
        int capacity = 4;
        assertFalse(carPooling(trips, capacity));
    }

    @Test
    public void test2() {
        int[][] trips = new int[][] {{2,1,5},{3,3,7}};
        int capacity = 5;
        assertTrue(carPooling(trips, capacity));
    }

    @Test
    public void test3() {
        int[][] trips = new int[][] {{2,1,5},{3,5,7}};
        int capacity = 3;
        assertTrue(carPooling(trips, capacity));
    }

    @Test
    public void test4() {
        int[][] trips = new int[][] {{3,2,7},{3,7,9},{8,3,9}};
        int capacity = 11;
        assertTrue(carPooling(trips, capacity));
    }
}
