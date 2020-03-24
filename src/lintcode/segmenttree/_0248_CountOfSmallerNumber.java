/*
Medium
#Binary Search, #Segment Tree

 */
package lintcode.segmenttree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 248. Count of Smaller Number
 *
 * Give you an integer array (index from 0 to n-1, where n is the size of this array,
 * value from 0 to 10000) and an query list. For each query, give you an integer,
 * return the number of element in the array that are smaller than the given integer.
 *
 * Example 1:
 * Input: array=[1,2,7,8,5] queries=[1,8,5]
 * Output:[0,4,2]
 *
 * Example 2:
 * Input: array=[3,4,5,8] queries=[2,4]
 * Output:[0,1]
 *
 * Challenge - Could you use three ways to do it.
 * - Just loop
 * - Sort and binary search
 * - Build Segment Tree and Search.
 */
public class _0248_CountOfSmallerNumber {

    /**
     * 线段树
     */
    public List<Integer> countOfSmallerNumber_SegmentTree(int[] A, int[] queries) {
        List<Integer> ans = new ArrayList<>();
        if (A == null)
            return ans;
    }

    class SegmentTreeNode {

    }


    /**
     * 排序 + 二分法
     * 易错点:
     * 1. 仍然是如下corner case
     *    input: array=[] queries=[1,2,3]
     *    Output:[0,0,0]
     */
    public List<Integer> countOfSmallerNumber_SortAndBinarySearch(int[] A, int[] queries) {
        List<Integer> ans = new ArrayList<>();
        if (A == null)
            return ans;

        Arrays.sort(A);

        for (int i : queries) {
            ans.add(findSmallerNumber(A, i));
        }

        return ans;
    }

    // 二分法 找出array中所有小于target的值, 统计数量
    private int findSmallerNumber(int[] array, int target) {
        if (array.length == 0)
            return 0;

        int l = 0, r = array.length - 1;

        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (array[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (array[l] >= target) {
            return l;
        }
        if (array[r] >= target) {
            return r;
        }
        return r + 1;
    }




    /**
     * 暴力解法
     * 易错点:
     * 1. 初始时, 不要判断 A.length == 0.　因为有如下corner case
     *    input: array=[] queries=[1,2,3]
     *  * Output:[0,0,0]
     */
    public List<Integer> countOfSmallerNumber_BruteForce(int[] A, int[] queries) {
        List<Integer> ans = new ArrayList<>();
        if (A == null) // 注意, 这里不能判断 A.length == 0
            return ans;

        for (int i : queries) {
            int count = 0;
            for (int j : A) {
                if (j < i) {
                    count++;
                }
            }
            ans.add(count);
        }

        return ans;
    }
}
