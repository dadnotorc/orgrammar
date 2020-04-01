/*
Hard
#Binary Search, #Binary Indexed Tree (Fenwick Tree), #Segment Tree

 */
package lintcode.segmenttree;

import java.util.ArrayList;
import java.util.List;

/**
 * 249. Count of Smaller Number before itself
 *
 * Give you an integer array (index from 0 to n-1, where n is the size of this array,
 * data value from 0 to 10000) . For each element Ai in the array, count the number
 * of element before this element Ai is smaller than it and return count number array.
 *
 * Example 1:
 * Input:  [1,2,7,8,5]
 * Output: [0,1,2,3,2]
 *
 * Example 2:
 * Input:  [7,8,2,1,3]
 * Output: [0,1,0,0,2]
 */
public class _0249_CountOfSmallerNumberBeforeItself {

    /**
     *
     * 1. 使用B数组记录每个下标值在A中出现的次数, 长度为10001. 使用线段树维护B数组
     * 2. 遍历A数组. 遇到i:
     *    a) 求B数组中下标0至(i-1)位的前缀和 ->　即为Ａ数组中小于i的元素个数
     *    b) 修改B数组中对应的值 (将B[i]加一), 并更新线段树
     *
     *
     * 别忘了 i==0 的特判
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        List<Integer> ans = new ArrayList<>();
        if (A == null)
            return ans;

        SegmentTree tree = new SegmentTree(10001);
//        int[] B = new int[10001];
//        for (int i : A) {
//            if (i == 0) {
//                ans.add(0);
//            } else {
//                ans.add(tree.query(0, i - 1));
//            }
//            B[i]++;
//            tree.modify(i, B[i]);
//        }

        // 使用addOne(), 则不需要创建B数组
        for (int i : A) {
            if (i == 0) {
                ans.add(0);
            } else {
                ans.add(tree.query(0, i - 1));
            }
            tree.addOne(i);
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

        // 新建值为0的线段树, 遍历A数组时, 更新此树
        public SegmentTree (int size) {
            this.size = size;
            root = buildTreeNode(0, size - 1);
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

        public int query(int queryStart, int queryEnd) {
            return query(root, queryStart, queryEnd);
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
                rightSum = query(node.right, Math.max(mid + 1, queryStart), queryEnd);
            }

            return leftSum + rightSum;
        }

        // 将B数组中index位赋值为val
        public void modify(int index, int val) {
            modify(root, index, val);
        }

        private void modify(SegmentTreeNode node, int index, int val) {
            if (node.start == node.end) { // 到达leaf node, 不许判断是否为index, 因为后续会确保走向index
                node.sum = val;
                return;
            }

            if (node.left.end >= index) {
                modify(node.left, index, val);
            } else {
                modify(node.right, index, val);
            }

            node.sum = node.left.sum + node.right.sum; // 别忘了更新当前位的sum
        }

        // 将B数组中index位的值加一
        public void addOne(int index) {
            addOne(root, index);
        }

        private void addOne(SegmentTreeNode node, int index) {
            if (node.start == node.end) {
                node.sum++;
                return;
            }

            if (node.left.end >= index) {
                addOne(node.left, index);
            } else {
                addOne(node.right, index);
            }

            node.sum++;
        }

    }


    /**
     * 暴力解法 - time: O(n^2), (1+2+3+...+(n-1))
     * 1. 遍历A数组. 遇到Ai, 再次遍历0至(Ai-1)位, 统计小于Ai的元素个数
     */
}
