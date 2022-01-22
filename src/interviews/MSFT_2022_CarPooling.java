package interviews;

import java.util.*;

/**
 * Car Pooling
 *
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that
 * the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi
 * respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 * Constraints:
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 *
 * leetcode 1094. Car Pooling
 */
public class MSFT_2022_CarPooling {

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
     * 1. 遍历 trips, 记录每一站(每一公里)有多少乘客
     * 2. 当某一站乘客数量超额, 则返回 false
     * 3. 全部结束后, 返回 true
     *
     * 时间 O(n) - 遍历 trips 数组. 之后遍历 changes 数组, 因为只有 1001 位, 仍是 O(1)
     * 空间 O(1) - 1001 位的数组
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 记录每一站上车下车的人数变化, 不包含当前车上的乘客总数,
        // 长度 为 1001, 因为 0 <= fromi < toi <= 1000
        int[] changes = new int[1001];

        // 这里不做当前乘客总数判断, 留在最后做. 因为 trips 没有遍历完, 不知道到站前车上乘客数量
        for (int[] trip : trips) {
            changes[trip[1]] += trip[0];
            changes[trip[2]] -= trip[0];
        }

        // 计算 prefix sum
        int curPassengers = 0;
        for (int i : changes) {
            curPassengers += i;
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
}
