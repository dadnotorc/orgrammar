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
     * 线段树 - time: O(mlogk)  m=queries.length, k=数组最大值
     * 在数组中寻找比 x 小的值的个数 = 数组中 0 到 (x-1) 个数之和, 及前缀和
     * 1. 新建 B 数组, 长度为10001 (因为 A 中的值是从0到10000). B 数组记录每个下标值在A中出现的次数
     * 2. 遍历 A 数组, 并更新 B 数组
     * 3. 使用线段树维护 B 数组
     * 4. 对queries中每一个 x, 在线段树上获得 0~(x-1) 的前缀和
     *
     * 易错点:
     * 1. 更新tree node时, index从0到10000, val为B[index]
     * 2. 查询queries里每个target时, 别忘了做特判 target 是否等于 0. 否则做query时, 会左右越界
     */
    public List<Integer> countOfSmallerNumber_SegmentTree(int[] A, int[] queries) {
        List<Integer> ans = new ArrayList<>();
        if (A == null)
            return ans;

        int[] B = new int[10001];
        for (int num : A) {
            B[num]++;
        }

        SegmentTree tree = new SegmentTree(10001);
        for (int i = 0; i < 10001; i++) { // 注意! 这里不能使用 for (int i : B)
            tree.modify(i, B[i]);
        }

        for (int target : queries) {
            if (target == 0) { // 注意! 被忘了特判
                ans.add(0);
            } else {
                ans.add(tree.query(0, target - 1));
            }
        }

        return ans;
    }

    class SegmentTreeNode {
        int sum, start, end;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            sum = 0;
            left = right = null;
        }
    }

    class SegmentTree {
        int size;
        SegmentTreeNode root;

        public SegmentTree(int size) {
            this.size = size;
            root = buildTreeNode(0, size - 1);
        }

        public int query(int queryStart, int queryEnd) {
            return query(root, queryStart, queryEnd);
        }

        public void modify(int index, int val) {
            modify(root, index, val);
        }

        private SegmentTreeNode buildTreeNode(int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);

            if (start == end)
                return node;

            int mid = (start + end) / 2;
            node.left = buildTreeNode(start, mid);
            node.right = buildTreeNode(mid + 1, end);
            return node;
        }

        private int query(SegmentTreeNode node, int queryStart, int queryEnd) {
            if (node.start == queryStart && node.end == queryEnd)
                return node.sum;

            int mid = (node.start + node.end) / 2;
            int leftSum = 0, rightSum = 0;
            if (queryStart <= mid) {
                leftSum = query(node.left, queryStart, Math.min(mid, queryEnd));
            }
            if (queryEnd >= mid + 1) {
                rightSum = query(node.right, Math.max(queryStart, mid + 1), queryEnd);
            }

            return leftSum + rightSum;
        }

        private void modify(SegmentTreeNode node, int index, int val) {
            if (node.start == node.end) {
                node.sum = val;
                return;
            }

            if (node.left.end >= index) {
                modify(node.left, index, val);
            } else {
                modify(node.right, index, val);
            }

            node.sum = node.left.sum + node.right.sum;
        }
    }


    /**
     * 排序 + 二分法 - time: O(nlogn + mlogn)  n=A.length, m=queries.length
     * 易错点:
     * 1. 仍然是如下corner case, 所以初始时, 不要判断 A.length == 0
     *    input: A=[] queries=[1,2,3]
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

        if (array[l] >= target) { // l-1 位最接近且小于target, 所以共有 l 个值小于target (0 到 l-1)
            return l;
        }
        if (array[r] >= target) { // l 位最接近target
            return r;
        }
        return r + 1; // r 位最接近target
    }




    /**
     * 暴力解法 - time: O(nm);  n=A.length, m=queries.length
     * 易错点:
     * 1. 初始时, 不要判断 A.length == 0.　因为有如下corner case
     *    input: A=[] queries=[1,2,3]
     *    Output:[0,0,0]
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
