/*
Medium
#Heap, #Quick Select, #Sort

 */
package lintcode;

/**
 * 606 - Kth Largest Element II
 *
 * Find K-th largest element in an array, and N is much larger than k.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Note: You can swap elements in the array
 *       Array is unsorted
 *       k is an integer from 1 to n
 *
 * Example 1:
 * Input:[9,3,2,4,8], k=3
 * Output:4
 *
 * Example 2:
 * Input:[1,2,3,4,5,6,8,9,10,7], k=10
 * Output:1
 *
 * 此题与 leetcode 215. Kth Largest Element in an Array 相同
 */
public class _0606_KthLargestElement2 {

    /**
     * 使用 Quick Sort 想法, 递归求解
     *
     * 此题也可以使用 PriorityQueue 求解. 思路是保持个长度为 k 的 max heap .
     * 将每个值 offer 进 pq, 如果 pq 长度超过k, poll出一位当前最小值. 之后, return pq.peek()
     */
    public int kthLargestElement2(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    public int quickSelect(int[] nums, int k, int l, int r) {
        int p = partition(nums, l, r);

        if (p == k - 1) {
            return nums[p];
        } else if (p < k - 1) {
            return quickSelect(nums, k, p + 1, r);
        } else {
            return quickSelect(nums, k, l, p - 1);
        }
    }

    public int partition(int[] nums, int l, int r) {
        // 大的放前面
        int pivot = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] > pivot) {
                if (i != j) {
                    swap(nums, i, j);
                }
                i++;
            }
        }

        swap(nums, i, r);
        return i;
    }

    // 另一种 partition 写法, 较快
    public int partition_2(int[] nums, int l, int r) {
        int i = l, j = r;
        int pivot = nums[l];

        while (i < j) {
            while (i < j && pivot >= nums[j]) {
                j--;
            }
            nums[i] = nums[j];
            while (i < j && pivot <= nums[i]) {
                i++;
            }
            nums[j] = nums[i];
        }

        nums[i] = pivot;

        return i;
    }

    public void swap(int[] nums, int l, int r) {
        int xor = nums[l] ^ nums[r];
        nums[l] = nums[l] ^ xor;
        nums[r] = nums[r] ^ xor;
    }
}
