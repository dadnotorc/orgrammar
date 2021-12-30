/*
Easy
#Greedy
LiveRamp
 */
package lintcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 1163 · Distribute Candies
 *
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies.
 * Each number means one candy of the corresponding kind. You need to distribute these candies equally in number
 * to brother and sister. Returns the maximum number of candy types available to sister.
 *
 * - The length of the given array is in range [2, 10,000], and will be even.
 * - The number in given array is in range [-100,000, 100,000].
 *
 * Example
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 * The sister has three different kinds of candies.
 *
 * 数组中, 每个数字代表当前糖果的类型
 * 数组的长度 = 糖果的数量
 * [1,1,2,2,3,3,] -> 有3种糖果, 1号 2号 3号. 每种糖果各两颗, 一共6颗
 */
public class _1163_DistributeCandies {

    /**
     * 1. 统计糖果种类 types - 将所有糖果类型加入 set
     * 2. 比较糖果类型 vs 糖果总数 / 2. 有如下两种可能
     *    - 类型 <= 糖果总数 / 2, 说明每种糖果至少有两颗, 可以给弟弟妹妹平分. 例如, 3种糖果共10颗. 所以, 所有类型都可供平分
     *    - 类型 > 糖果总数 / 2, 说明有些糖果不够平分. 也就是说, 可供平分的类型最多只有 (总数 / 2) 种.
     * 3. 所以返回上面两者的较小者即可
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> types = new HashSet<>();
        for (int i : candies) {
            types.add(i);
        }
        return Math.min(types.size(), candies.length / 2);
    }

    /**
     * 优化 - 观察之前的两种可能: 类型较少时, 返回类型数量; 否则, 返回 糖果总数 / 2
     * 所以可以得出结论, 返回值的上限就是 糖果总数 / 2.
     */
    public int distributeCandies_2(int[] candies) {
        Set<Integer> set = new HashSet<>();
        int types = 0; // 统计当前已知的类型
        int n = candies.length;
        for (int candy : candies) {
            if (types < n / 2) {
                if (!set.contains(candy)) {
                    set.add(candy);
                    types++;
                }
            } else {
                break;
            }
        }
        return types;
    }


    /**
     * 有 bug, 如下测试会出错 - 题意理解有误
     * distribute these candies equally in number 的意思是, 糖果数量要对半分, 而不是只有当每种糖果超过 2 个时才各拿一个
     * 例如 100 颗糖, 其中有 94 种不同类型. 哥哥拿 50 颗, 妹妹拿 50 颗.
     * 这 50 颗种 最多有多少种不同类型的糖果呢? 答案是 50 种
     *
     * 再例如 100 颗糖, 其中有 10 种不同类型 (每种 10 颗).
     * 妹妹拿的这 50 颗里, 最多也只有 10 种不同类型.
     *
     *
     * [505,8,951,606,475,401,451,903,618,772,760,475,310,417,728,972,646,794,648,315,353,698,55,88,503,798,297,139,879,99,
     * 917,38,554,747,561,175,956,373,672,941,267,680,89,902,127,428,545,914,586,349,339,152,185,340,220,547,648,364,939,
     * 641,212,422,621,512,338,826,887,813,125,955,4,874,804,868,231,939,114,237,298,606,199,965,972,141,676,90,369,289,
     * 628,12,588,236,254,370,920,298,566,888,316,173]
     *　expected = 50
     */
//    public int distributeCandies_bug(int[] candies) {
//        int ans = 0;
//        int[] counts = new int[200001];
//        Arrays.fill(counts, 0);
//        for (int candy : candies) {
//            counts[candy + 100000]++;
//        }
//        for (int count : counts) {
//            if (count > 1) {
//                ans++;
//            }
//        }
//        return ans;
//    }

    @Test
    public void test1() {
        // candies.length = 100, types = 94
        int[] candies = {505,8,951,606,475,401,451,903,618,772,760,475,310,417,728,972,646,794,648,315,353,698,55,88,503,
                798,297,139,879,99,917,38,554,747,561,175,956,373,672,941,267,680,89,902,127,428,545,914,586,349,339,152,
                185,340,220,547,648,364,939,641,212,422,621,512,338,826,887,813,125,955,4,874,804,868,231,939,114,237,298,
                606,199,965,972,141,676,90,369,289,628,12,588,236,254,370,920,298,566,888,316,173};
        org.junit.Assert.assertEquals(50, distributeCandies(candies));
    }
}
